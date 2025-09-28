package com.dto;

import jakarta.validation.constraints.NotBlank;

public class NfcScanDTO {

    @NotBlank(message = "O campo cardCode não pode estar em branco")
    private String cardCode;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}
