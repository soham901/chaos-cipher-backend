package com.chaoscipher.chaoscipher.services;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.chaoscipher.chaoscipher.entities.EncryptionEntity;
import com.chaoscipher.chaoscipher.repositories.EncryptionRepository;

@Service
public class EncryptionService {
    private final EncryptionRepository encryptionRepository;

    private final int mincodeLength = 2;
    private final int maxcodeLength = 4;

    public EncryptionService(EncryptionRepository encryptionRepository) {
        this.encryptionRepository = encryptionRepository;
    }

    public String encryptPlainText(String plainText) {
        HashMap<String, String> map = generateRandomHashMap(mincodeLength, maxcodeLength, plainText);

        String id = saveHashMap(map);

        StringBuilder cipherText = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            String charStr = String.valueOf(c);
            cipherText.append(map.getOrDefault(charStr, charStr));
        }

        return cipherText.toString() + id;
    }

    private static HashMap<String, String> generateRandomHashMap(int mincodeLength, int maxcodeLength,
            String characString) {
        HashMap<String, String> map = new HashMap<>();
        String characters = characString;
        Random random = new Random();

        for (char c : characters.toCharArray()) {
            map.put(Character.toString(c), generateRandomCode(
                    mincodeLength + random.nextInt(maxcodeLength - mincodeLength + 1)));
        }
        return map;
    }

    private static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    public String saveHashMap(HashMap<String, String> map) {
        EncryptionEntity encryptionEntity = new EncryptionEntity();
        encryptionEntity.setData(map);

        encryptionEntity = encryptionRepository.save(encryptionEntity);
        return encryptionEntity.getId();
    }

    public String decryptPlainText(String plainText) {
        // get last 24 characters as id and others as encrypted string
        String id = plainText.substring(plainText.length() - 24);
        String encryptedString = plainText.substring(0, plainText.length() - 24);

        EncryptionEntity encryptionEntity = encryptionRepository.findById(id).orElse(null);
        if (encryptionEntity == null) {
            throw new IllegalArgumentException("Invalid encryption id");
        }

        HashMap<String, String> map = encryptionEntity.getData();

        return decryptEncryptedString(encryptedString, map);
    }

    private static String decryptEncryptedString(String encryptedString, HashMap<String, String> map) {
        StringBuilder password = new StringBuilder();

        while (!encryptedString.isEmpty()) {
            boolean matched = false; // Flag to check if a match was found in the current iteration

            for (HashMap.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue(); // This is the encrypted part
                String key = entry.getKey(); // This is the original character

                if (encryptedString.startsWith(value)) {
                    password.append(key); // Append the original character to the result
                    encryptedString = encryptedString.substring(value.length()); // Remove the matched encrypted part
                    matched = true;
                    break; // Break the loop to start again with the modified string
                }
            }

            if (!matched) {
                // If no match was found, it means something is wrong with the encrypted string
                // or the map
                throw new IllegalArgumentException("Invalid encrypted string or map provided");
            }
        }

        return password.toString();
    }
}