package com.chaoscipher.chaoscipher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public EncryptionResponse encryptPlainText(@RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getData();
        String cipherText = encryptionService.encryptPlainText(data);
        return new EncryptionResponse(cipherText);
    }

    @PostMapping(path = "/decrypt")
    public EncryptionResponse decryptPlainText(@RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getData();
        String plainText = encryptionService.decryptPlainText(data);
        return new EncryptionResponse(plainText);
    }
}