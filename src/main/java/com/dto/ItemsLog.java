package com.dto;

import java.io.Serializable;

public class ItemsLog implements Serializable {
    private String itemEanCode;
    private String itemName;
    private int quantity;

    public ItemsLog() {
    }

    public ItemsLog(String itemEanCode, String itemName, int quantity) {
        this.itemEanCode = itemEanCode;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemEanCode() {
        return itemEanCode;
    }

    public void setItemEanCode(String itemEanCode) {
        this.itemEanCode = itemEanCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
