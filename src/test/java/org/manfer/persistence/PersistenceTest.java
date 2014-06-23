package org.manfer.persistence;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.manfer.dto.Dto;
import org.manfer.dto.PlayerDto;
import org.manfer.persistence.repositories.MongoDtoRepository;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;



/**
 * Unit tests for the main interface of the persistence layer.
 * 
 * @author marcandreuf
 */
public class PersistenceTest {    
    
    private RepositoryFactorySupport mock_repoFactory;
    private MongoTemplate mock_mongoTemplate;
    private Map<Class ,Class> mock_map;
    private Persistence persistence;
    
    private final PlayerDto mock_player = mock(PlayerDto.class);
    private final Object mongoRepositoryType = mock(Object.class);
    private final MongoDtoRepository mock_mongoDtoRepository = mock(MongoDtoRepository.class);
    private final Class mongoRepositoryClass = mongoRepositoryType.getClass();
    
    public PersistenceTest(){
        mock_repoFactory = mock(MongoRepositoryFactory.class);
        mock_mongoTemplate = mock(MongoTemplate.class);
        mock_map = mock(Map.class);
        persistence = new PersistenceMongoImpl(mock_mongoTemplate, mock_map, mock_repoFactory);
    }
    

    @Test
    public void shouldTestThatIsLoadingTheCollectionsListAsConnectionTest() {
        persistence.isConnected();
        verify(mock_mongoTemplate).getCollectionNames();
    }

    
    @Test
    public void shouldTestTheCallsToStoreAPlayerUsingTheRegisteredMongoDtoRepository() {
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);
        
        persistence.save(mock_player);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).save(mock_player);
    }
    

    @Test
    public void shouldTestTheCallsToStoreAListOfPlayersUsingTheRegisteredMongoDtoRepository() {
        List mock_lstPlayer = mock(List.class);
        
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);
        when(mock_lstPlayer.get(0)).thenReturn(mock_player);
        
        persistence.saveAll(mock_lstPlayer);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).save(mock_lstPlayer);
    }


    @Test
    public void shouldTestTheCallsToDeleteAPlayerUsingTheRegisteredMongoDtoRepository() {
        PlayerDto playerToDelete = mock(PlayerDto.class);
        
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);

        persistence.delete(playerToDelete, PlayerDto.class);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).delete(playerToDelete);
    }


    @Test
    public void shouldTestTheCallsToDeleteACollectionTypeUsingTheRegisteredMongoDtoRepository() {
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);

        persistence.deleteAll(PlayerDto.class);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).deleteAll();        
    }
    
    
    @Test
    public void shouldTestTheCallsToFindByIdUsingTheRegisteredMongoDtoRepository() {
        String sample_id = "00001";
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);

        persistence.findOne(sample_id, mongoRepositoryClass);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).findOne(sample_id);
    }
    
    
    @Test
    public void shouldTestTheCallsToFindByNameUsingTheRegisteredMongoDtoRepository() {
        String sample_name = "testName";
        
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);

        persistence.findByName(sample_name, mongoRepositoryClass);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).findByName(sample_name);
    }
    
    
    @Test
    public void shouldTestTheCallsToFindAllUsingTheRegisteredMongoDtoRepository() {
        when(mock_map.get(any(Dto.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);

        persistence.findAll(mongoRepositoryClass);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).findAll();
    }

}
