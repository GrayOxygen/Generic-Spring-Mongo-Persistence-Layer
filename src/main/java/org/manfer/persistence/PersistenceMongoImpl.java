package org.manfer.persistence;

import java.util.List;
import java.util.Map;
import org.manfer.dto.Dto;
import org.manfer.persistence.repositories.MongoRepositoryDto;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * Implementation of the persistence layer interface for MongoDB.
 */
public class PersistenceMongoImpl implements Persistence {

    private final MongoTemplate mongoTemplate;
    private final Map<Class, Class> mongoDtoRepositoryTypeMapping;
    private final RepositoryFactorySupport repoFactory;
    
    private MongoRepositoryDto mongoRepository;

    public PersistenceMongoImpl(MongoTemplate mongoTemplate, Map<Class, Class> mongoRepositoryTypesMapping){
        this.mongoTemplate = mongoTemplate;
        this.mongoDtoRepositoryTypeMapping = mongoRepositoryTypesMapping;
        this.repoFactory = new MongoRepositoryFactory(mongoTemplate);
    }
    
    
    @Override
    public boolean isConnected() {        
        return !mongoTemplate.getCollectionNames().isEmpty();
    }

    @Override
    public <T extends Dto> void save(T dto) {        
        mongoRepository = getMongoRepositoryInstance(dto.getClass());       
        mongoRepository.save(dto);
    }

    private <T extends Dto> MongoRepositoryDto getMongoRepositoryInstance(Class dtoType) {
        Class mongoRepositoryType = mongoDtoRepositoryTypeMapping.get(dtoType);
        return (MongoRepositoryDto) repoFactory.getRepository(mongoRepositoryType);        
    }
    
    
    @Override
    public <T extends Dto> void deleteAll(Class<T> collection) {
        mongoRepository = getMongoRepositoryInstance(collection);  
        mongoRepository.deleteAll();
    }
    
    
    @Override
    public <T extends Dto> void saveAll(List<T> lstPlayers) {
        mongoRepository = getMongoRepositoryInstance(lstPlayers.get(0).getClass());
        mongoRepository.save(lstPlayers);
    }
    

    @Override
    public <T extends Dto> T findOne(String id, Class<T> collection) {
        mongoRepository = getMongoRepositoryInstance(collection);
        return (T) mongoRepository.findOne(id);       
    }


    @Override
    public <T extends Dto> List<T> findAll(Class<T> collection) {
        mongoRepository = getMongoRepositoryInstance(collection);
        return mongoRepository.findAll();
    }

    @Override
    public <T extends Dto> void delete(T dto, Class<T> collection) {
        mongoRepository = getMongoRepositoryInstance(collection);
        mongoRepository.delete(dto);
    }

    @Override
    public <T extends Dto> void findByName(String name, Class<T> collection) {
        mongoRepository = getMongoRepositoryInstance(collection);
        mongoRepository.findByName(name);
    }


}
