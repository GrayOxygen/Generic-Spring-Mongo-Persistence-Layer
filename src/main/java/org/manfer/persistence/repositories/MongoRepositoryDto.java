package org.manfer.persistence.repositories;

import java.io.Serializable;
import org.manfer.dto.Dto;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoRepositoryDto<T extends Dto, ID extends Serializable> extends MongoRepository<T, ID> {
    
    public T findByName(String name);

}
