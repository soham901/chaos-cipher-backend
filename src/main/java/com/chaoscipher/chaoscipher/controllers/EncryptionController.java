package com.chaoscipher.chaoscipher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaoscipher.chaoscipher.services.EncryptionService;

@RestController
public class EncryptionController {
    @Autowired
    private EncryptionService encryptionService;

    @GetMapping(path = "/encrypt")
    public String encryptPlainText(@RequestParam("data") String data) {
        String fakeCipher = encryptionService.encryptPlainText(data);
        return fakeCipher;
    }

    @GetMapping(path = "/decrypt")
    public String decryptPlainText(@RequestParam("data") String data) {
        return encryptionService.decryptPlainText(data);
    }
}