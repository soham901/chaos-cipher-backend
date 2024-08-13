package com.chaoscipher.chaoscipher.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.chaoscipher.chaoscipher.entities.EncryptionEntity;

public interface EncryptionRepository extends MongoRepository<EncryptionEntity, String> {
}