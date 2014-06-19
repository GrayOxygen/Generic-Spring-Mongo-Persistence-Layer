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


/**
 * Spring configuration beans.
 * 
 * @author marcandreuf
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.manfer.persistence.repositories")
public class PersistenceContextConfiguration {

    /**
     * MongoDB factory Bean.
     * This is the bean to setup connection details like the DB name, user, pass, etc...
     * 
     * @return 
     * @throws java.lang.Exception
     */
    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "test");
    }

    /**
     * MongoTemplate to perform actions to MongoDB.
     * This is the bean that handles all interactions with MongoDB.
     * 
     * @return 
     * @throws java.lang.Exception
     */
    public @Bean MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }    
    
    /**
     * Main bean to perform all persistence actions from the application level.
     * All interaction with the DB from the application code should go through this interface.
     * 
     * @return 
     * @throws java.lang.Exception
     */
    public @Bean Persistence mongoPersistenceImpl() throws Exception {
        return new PersistenceMongoImpl(mongoTemplate(), registrationMappingDtoVsRepository());
    }

    /*
    * This is the point where we register which Repository needs to be used for each Dto type at runtime.
    * This gives control to the developer to choose the exact mapping instead of relying on runtime discovery which 
    * could find multiple repository implementions for the same Dto type.
    */
    private Map<Class, Class> registrationMappingDtoVsRepository() throws Exception {        
        Map<Class, Class> mapTypes = new HashMap<>();
        
        mapTypes.put(PlayerDto.class, PlayerMongoRepository.class); 
        
        return mapTypes;
    }
    
}
