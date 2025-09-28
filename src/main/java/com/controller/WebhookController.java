package com.controller;

import com.dto.NfcScanDTO;
import com.dto.UserDTO;
import com.dto.WebSocketErrorPayload;
import com.dto.WebSocketSuccessPayload;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    @Value("${nfc.api-key}")
    private String validApiKey;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/nfc-scan")
    public ResponseEntity<Void> handleNfcScan(@RequestBody NfcScanDTO payload, @RequestHeader("API-Key") String apiKey) {
        if (!validApiKey.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserDTO> userOptional = userService.getUserByCardCode(payload.getCardCode());

        if (userOptional.isEmpty()) {
            messagingTemplate.convertAndSend("/topic/scan-events", new WebSocketErrorPayload("Usuário não encontrado para o cartão fornecido."));
            return ResponseEntity.notFound().build();
        }

        messagingTemplate.convertAndSend("/topic/scan-events", new WebSocketSuccessPayload(userOptional.get()));
        return ResponseEntity.ok().build();
    }
}
