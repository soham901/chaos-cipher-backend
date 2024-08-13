package com.chaoscipher.chaoscipher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptionDTO {
    private String id;
    private String plainText;
    private String cipherText;
}