package org.manfer.persistence.repositories;

import java.io.Serializable;
import org.manfer.dto.Dto;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * This is the custom interface for specific actions on top level DTO objects. 
 * 
 * @author marcandreuf
 * @param <T> specific type to be persisted.
 * @param <ID> type of the id field.
 */
public interface MongoDtoRepository<T extends Dto, ID extends Serializable> extends MongoRepository<T, ID> {
    
    public T findByName(String name);

}
