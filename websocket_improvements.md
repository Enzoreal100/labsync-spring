# Melhorias na Arquitetura WebSocket para Notificações Direcionadas

Este documento descreve uma arquitetura aprimorada para o sistema de WebSockets, permitindo que as notificações de leitura NFC sejam enviadas apenas para os clientes (front-ends) relevantes, em vez de para todos os clientes conectados.

## Problema

A implementação atual utiliza um tópico único e global (`/topic/scan-events`). Quando o webhook processa uma leitura de cartão, ele publica uma mensagem nesse tópico. Como resultado, o message broker distribui essa mensagem para **todos** os clientes front-end que estão inscritos, independentemente de qual laboratório ou estação a leitura se originou.

Isso não é ideal para cenários onde cada front-end deve responder apenas a eventos que ocorrem em seu próprio contexto (por exemplo, em seu próprio laboratório).

## Solução Proposta: Tópicos Dinâmicos por Grupo

A solução é abandonar o tópico global em favor de **tópicos dinâmicos**, com escopo definido por um identificador de grupo, como o `labId`.

O fluxo de trabalho seria o seguinte:

1.  **Inscrição Específica:** Cada cliente front-end, ao se conectar, se inscreve em um tópico que corresponde ao seu laboratório. Por exemplo, o front-end do "Laboratório A" (ID: 1) se inscreve em `/topic/scan-events/1`.
2.  **Requisição Contextualizada:** O script do leitor NFC, ao enviar os dados para o webhook, inclui o `labId` da estação onde a leitura ocorreu.
3.  **Publicação Direcionada:** O backend utiliza o `labId` para determinar o tópico exato para o qual a mensagem deve ser enviada.

Isso transforma o modelo de "broadcast" (um-para-todos) em um modelo "multicast" (um-para-muitos, mas restrito a um grupo).

## Passos de Implementação

### 1. Backend

#### a. Atualizar o DTO da Requisição

O `NfcScanDTO.java` precisa ser modificado para incluir o `labId`.

**Arquivo:** `src/main/java/com/dto/NfcScanDTO.java`
```java
// Conteúdo proposto
public class NfcScanDTO {
    private String cardCode;
    private Long labId; // Adicionar este campo
    // getters e setters
}
```

#### b. Atualizar a Lógica do Controller

O `WebhookController.java` deve ser ajustado para usar o `labId` para construir o nome do tópico dinamicamente.

**Arquivo:** `src/main/java/com/controller/WebhookController.java`
```java
// Lógica proposta
@PostMapping("/nfc-scan")
public ResponseEntity<Void> handleNfcScan(@RequestBody NfcScanDTO payload, @RequestHeader("API-Key") String apiKey) {
    // ... validação ...

    String topic = "/topic/scan-events/" + payload.getLabId(); // Constrói o tópico dinâmico
    Optional<UserDTO> userOptional = userService.getUserByCardCode(payload.getCardCode());

    if (userOptional.isEmpty()) {
        // Envia o erro para o tópico específico do laboratório que fez a leitura
        messagingTemplate.convertAndSend(topic, new WebSocketErrorPayload("Usuário não encontrado..."));
        return ResponseEntity.notFound().build();
    }

    // Envia o sucesso para o tópico específico do laboratório
    messagingTemplate.convertAndSend(topic, new WebSocketSuccessPayload(userOptional.get()));
    return ResponseEntity.ok().build();
}
```

### 2. Script Local (Leitor NFC)

O script que envia a requisição para o webhook deve ser atualizado para incluir o `labId` no corpo (body) da requisição `POST`.

```python
# Exemplo de corpo da requisição
payload = {
    "cardCode": "UID_DO_CARTAO_LIDO",
    "labId": 1  # ID do laboratório onde o leitor está
}

# requests.post(url, json=payload, headers=headers)
```

### 3. Front-end

A lógica do cliente front-end que estabelece a conexão WebSocket deve ser modificada para se inscrever no tópico correto. O `labId` deve estar disponível para o front-end.

```javascript
// Exemplo com Stomp.js
const stompClient = ...;
const labId = 1; // O ID do laboratório deste front-end

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    
    // Inscrição no tópico dinâmico
    stompClient.subscribe('/topic/scan-events/' + labId, function (message) {
        const payload = JSON.parse(message.body);
        // Processar a mensagem recebida (dados do usuário ou erro)
    });
});
```
