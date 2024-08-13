package com.chaoscipher.chaoscipher.entities;

import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "encryptions")
public class EncryptionEntity {
    @Id
    private String id;
    private HashMap<String, String> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
