# Plano de Execução: Módulo de Controle de Acesso por NFC

Este plano detalha as tarefas a serem executadas por um agente de IA para implementar a funcionalidade de leitura de cartão NFC, conforme as especificações do arquivo `specs.md`. As tarefas estão organizadas por componente (Backend, Script Local) para um desenvolvimento passo a passo.

## Fase 1: Backend (API Java/Spring Boot)

O objetivo é preparar a API para receber os dados do leitor NFC, processá-los e notificar o front-end em tempo real via WebSockets.

### 1.1. Preparar o Modelo de Dados
- [ ] **Tarefa 1: Modificar a Entidade `User`**
  - **Arquivo:** `src/main/java/com/labsync/entity/User.java`
  - **Ação:** Veja se pode utilizar o campo cardCode, se não puder: Adicionar um novo campo para armazenar o ID do cartão NFC. Este campo deve ser único.
  - **Código:**
    ```java
    @Column(name = "nfc_card_id", unique = true)
    private String nfcCardId;
    ```
  - **Observação:** Não se esqueça de adicionar os getters e setters correspondentes. Em caso de já estar pronto, pular a tarefa 2.

- [ ] **Tarefa 2: Atualizar o Banco de Dados**
  - **Ação:** Criar um novo arquivo de migração Flyway para adicionar a coluna `nfc_card_id` à tabela `users`.
  - **Novo Arquivo:** `src/main/resources/db/migration/V7__add_nfc_card_id_to_users_table.sql`
  - **Conteúdo SQL:**
    ```sql
    ALTER TABLE users ADD COLUMN nfc_card_id VARCHAR(255);
    CREATE UNIQUE INDEX idx_nfc_card_id ON users (nfc_card_id);
    ```

### 1.2. Criar o Endpoint de Webhook
- [ ] **Tarefa 3: Criar DTO para o Webhook**
  - **Ação:** Criar uma classe Data Transfer Object (DTO) para o corpo da requisição do webhook.
  - **Novo Arquivo:** `src/main/java/com/labsync/dto/NfcScanDTO.java`
  - **Conteúdo:**
    ```java
    package com.labsync.dto;

    import lombok.Data;

    @Data
    public class NfcScanDTO {
        private String cardCode;
    }
    ```

- [ ] **Tarefa 4: Adicionar Método ao Repositório de Usuário**
  - **Arquivo:** `src/main/java/com/labsync/repository/UserRepository.java`
  - **Ação:** Identifique e veja a possível solução já pronta com findByCardCode ou similar. Adicionar um método para buscar um usuário pelo ID do cartão NFC caso a solução já pronta não seja possível. 
  - **Código:**
    ```java
    import java.util.Optional;
    
    // ...
    
    Optional<User> findByNfcCardId(String nfcCardId);
    ```

- [ ] **Tarefa 5: Criar o Controller do Webhook**
  - **Ação:** Criar um novo `RestController` para lidar com as requisições do script local.
  - **Novo Arquivo:** `src/main/java/com/labsync/controller/WebhookController.java`
  - **Estrutura Inicial:**
    ```java
    package com.labsync.controller;

    import com.labsync.dto.NfcScanDTO;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    
    @RestController
    @RequestMapping("/api/webhooks")
    public class WebhookController {
    
        @PostMapping("/nfc-scan")
        public ResponseEntity<Void> handleNfcScan(@RequestBody NfcScanDTO payload, @RequestHeader("API-Key") String apiKey) {
            // Lógica a ser implementada
            return ResponseEntity.ok().build();
        }
    }
    ```

### 1.3. Implementar Lógica de Negócio e Segurança
- [ ] **Tarefa 6: Implementar Segurança com API Key**
  - **Arquivo:** `src/main/resources/application.properties`
  - **Ação:** Adicionar a chave de API secreta.
  - **Configuração:** `nfc.api-key=CHAVE_SECRETA_MUITO_SEGURA_123`
  - **Arquivo:** `src/main/java/com/labsync/controller/WebhookController.java`
  - **Ação:** Validar a API Key recebida no header. Se a chave for inválida, retornar `401 Unauthorized`.

- [ ] **Tarefa 7: Implementar o Serviço do Webhook**
  - **Arquivo:** `src/main/java/com/labsync/service/UserService.java`
  - **Ação:** Criar um método que utiliza o `UserRepository` para encontrar o usuário pelo `nfcCardId`.
  - **Arquivo:** `src/main/java/com/labsync/controller/WebhookController.java`
  - **Ação:** Injetar o `UserService` e chamar o método. Se o usuário não for encontrado, retornar `404 Not Found`.

### 1.4. Configurar Comunicação via WebSocket
- [ ] **Tarefa 8: Adicionar Dependência WebSocket**
  - **Arquivo:** `pom.xml`
  - **Ação:** Adicionar a dependência `spring-boot-starter-websocket`.

- [ ] **Tarefa 9: Configurar WebSocket**
  - **Ação:** Criar uma classe de configuração para habilitar o WebSocket e definir os endpoints.
  - **Novo Arquivo:** `src/main/java/com/labsync/config/WebSocketConfig.java`
  - **Conteúdo:** Configurar um endpoint (ex: `/ws`) e um broker de mensagens (ex: `/topic`).

- [ ] **Tarefa 10: Criar Payloads do WebSocket**
  - **Ação:** Criar DTOs para as mensagens de sucesso e erro que serão enviadas ao front-end.
  - **Novos Arquivos:** `WebSocketSuccessPayload.java` e `WebSocketErrorPayload.java` em `src/main/java/com/labsync/dto/`.

- [ ] **Tarefa 11: Integrar Webhook com WebSocket**
  - **Arquivo:** `src/main/java/com/labsync/controller/WebhookController.java`
  - **Ação:** Injetar `SimpMessagingTemplate` e usá-lo para enviar a mensagem para o tópico `/topic/scan-events` após processar o webhook (seja com sucesso ou erro).

## Fase 2: Script da Estação Local (Python)

O objetivo é criar um script que roda na máquina conectada ao leitor NFC, captura o ID do cartão e o envia para a API.

- [ ] **Tarefa 12: Criar Arquivo do Script**
  - **Ação:** Criar um novo arquivo `nfc_reader.py` na raiz do projeto (ou em uma nova pasta `scripts`).

- [ ] **Tarefa 13: Implementar Lógica de Leitura**
  - **Ação:** O script deve ler continuamente a entrada do dispositivo. Para fins de desenvolvimento, pode-se simular a leitura com a função `input()` do Python.

- [ ] **Tarefa 14: Implementar Envio para o Webhook**
  - **Ação:** Usar a biblioteca `requests` para enviar uma requisição `HTTP POST` para o endpoint `/api/webhooks/nfc-scan`.
  - **Detalhes:**
    - Incluir o `Content-Type: application/json`.
    - Incluir o header `API-Key` com o token secreto.
    - Enviar o `cardId` no corpo da requisição.

- [ ] **Tarefa 15: Adicionar Logging e Tratamento de Erros**
  - **Ação:** Usar a biblioteca `logging` para registrar o resultado de cada tentativa de envio (sucesso ou falha). O script não deve parar se a API estiver offline.

- [ ] **Tarefa 16: Criar Documentação para o Script**
  - **Ação:** Criar um `README.md` na mesma pasta do script, explicando como instalar as dependências (`pip install requests`), configurar a URL da API e a API Key, e como executá-lo.

## Fase 3: Front-End (Conceitual)

Esta fase descreve as tarefas necessárias no lado do cliente, que não serão implementadas diretamente pelo agente no backend.

- [ ] **Tarefa 17: Conectar ao WebSocket**
  - **Ação:** Ao carregar a página principal, a aplicação deve estabelecer uma conexão com o endpoint `/ws` da API usando bibliotecas como `SockJS` e `Stomp.js`.

- [ ] **Tarefa 18: Inscrever-se no Tópico**
  - **Ação:** Após a conexão, a aplicação deve se inscrever no tópico `/topic/scan-events` para receber as notificações.

- [ ] **Tarefa 19: Gerenciar o Estado da UI**
  - **Ação:** Implementar um handler que processa as mensagens recebidas:
    - **Sucesso:** Se a mensagem indicar sucesso, extrair os dados do usuário e atualizar a interface para exibi-los.
    - **Erro:** Se a mensagem indicar erro (cartão não encontrado), exibir uma notificação temporária para o operador.
