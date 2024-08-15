package com.chaoscipher.chaoscipher.dto;

public class EncryptionResponse {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public EncryptionResponse(String data) {
        this.data = data;
    }
}
