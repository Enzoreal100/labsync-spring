package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BarcodeScanDTO {

    @NotBlank(message = "O campo barcodeData não pode estar em branco")
    private String barcodeData;

    @NotNull(message = "O campo labId não pode ser nulo")
    private Integer labId;

    public String getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(String barcodeData) {
        this.barcodeData = barcodeData;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }
}
