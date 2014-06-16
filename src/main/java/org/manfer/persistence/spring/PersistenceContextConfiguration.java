package org.manfer.persistence.spring;

import com.mongodb.MongoClient;
import java.util.HashMap;
import java.util.Map;
import org.manfer.dto.PlayerDto;
import org.manfer.persistence.Persistence;
import org.manfer.persistence.PersistenceMongoImpl;
import org.manfer.persistence.repositories.PlayerMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.manfer.persistence.repositories")
public class PersistenceContextConfiguration {

    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "test");
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }    
    
    
    public @Bean Persistence mongoPersistenceImpl() throws Exception {
        return new PersistenceMongoImpl(mongoTemplate(), mongoRepositoryFactory());
    }
    
    private Map<Class, Class> mongoRepositoryFactory() throws Exception {        
        Map<Class, Class> mapTypes = new HashMap<>();        
        mapTypes.put(PlayerDto.class, PlayerMongoRepository.class);        
        return mapTypes;
    }
    
}
