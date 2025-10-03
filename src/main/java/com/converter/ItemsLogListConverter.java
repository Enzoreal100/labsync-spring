package com.converter;

import com.dto.ItemsLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;

@Converter
public class ItemsLogListConverter implements AttributeConverter<ArrayList<ItemsLog>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ArrayList<ItemsLog> itemsLogs) {
        try {
            return objectMapper.writeValueAsString(itemsLogs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }

    @Override
    public ArrayList<ItemsLog> convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Verifica se é dado serializado antigo (começa com ACED)
        if (json.startsWith("ACED")) {
            // Retorna lista vazia para dados legados ou implemente deserialização
            return new ArrayList<>();
        }
        
        try {
            return objectMapper.readValue(json, new TypeReference<ArrayList<ItemsLog>>() {});
        } catch (JsonProcessingException e) {
            // Se falhar, retorna lista vazia
            return new ArrayList<>();
        }
    }
}