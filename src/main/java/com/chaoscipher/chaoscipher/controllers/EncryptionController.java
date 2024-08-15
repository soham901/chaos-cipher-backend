package com.chaoscipher.chaoscipher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chaoscipher.chaoscipher.dto.EncryptionRequest;
import com.chaoscipher.chaoscipher.dto.EncryptionResponse;
import com.chaoscipher.chaoscipher.services.EncryptionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EncryptionController {
    @Autowired
    private EncryptionService encryptionService;

    @PostMapping(path = "/encrypt")
    public ResponseEntity<EncryptionResponse> encryptPlainText(@RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getData();

        // Handle null or empty data
        if (data == null || data.isEmpty()) {
            return ResponseEntity.badRequest().body(new EncryptionResponse("Input text cannot be null or empty"));
        }

        String cipherText = encryptionService.encryptPlainText(data);
        return ResponseEntity.ok(new EncryptionResponse(cipherText));
    }

    @PostMapping(path = "/decrypt")
    public ResponseEntity<EncryptionResponse> decryptPlainText(@RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getData();

        // Handle null or empty data
        if (data == null || data.isEmpty()) {
            return ResponseEntity.badRequest().body(new EncryptionResponse("Input text cannot be null or empty"));
        }

        String plainText = encryptionService.decryptPlainText(data);
        return ResponseEntity.ok(new EncryptionResponse(plainText));
    }

}