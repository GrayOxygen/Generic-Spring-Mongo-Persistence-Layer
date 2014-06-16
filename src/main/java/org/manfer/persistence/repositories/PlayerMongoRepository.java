package org.manfer.persistence.repositories;

import org.manfer.dto.PlayerDto;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo repository for PlayerDto
 */
public interface PlayerMongoRepository extends MongoRepository<PlayerDto, String> {}
