package com.controller;

import jakarta.validation.Valid;
import com.dto.*;
import com.service.StockService;
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
    private StockService stockService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/nfc-scan")
    public ResponseEntity<Void> handleNfcScan(@Valid @RequestBody NfcScanDTO payload, @RequestHeader("API-Key") String apiKey) {
        if (!validApiKey.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<UserDTO> userOptional = userService.getUserByCardCode(payload.getCardCode());

        // This implementation sends to a general topic. 
        // For targeted messages, a labId would be needed in the NfcScanDTO.
        if (userOptional.isEmpty()) {
            messagingTemplate.convertAndSend("/topic/scan-events", new WebSocketErrorPayload("Usuário não encontrado para o cartão fornecido."));
            return ResponseEntity.notFound().build();
        }

        messagingTemplate.convertAndSend("/topic/scan-events", new WebSocketSuccessPayload(userOptional.get()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/barcode-scan")
    public ResponseEntity<Void> handleBarcodeScan(@Valid @RequestBody BarcodeScanDTO payload, @RequestHeader("API-Key") String apiKey) {
        if (!validApiKey.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<StockDTO> stockOptional = stockService.getStockByEanCodeAndLabId(payload.getBarcodeData(), payload.getLabId());

        String topic = "/topic/barcode-scan-events/" + payload.getLabId();

        if (stockOptional.isEmpty()) {
            messagingTemplate.convertAndSend(topic, new WebSocketErrorPayload("Item não encontrado no estoque deste laboratório."));
            return ResponseEntity.notFound().build();
        }

        messagingTemplate.convertAndSend(topic, new WebSocketSuccessPayload(stockOptional.get()));
        return ResponseEntity.ok().build();
    }
}
