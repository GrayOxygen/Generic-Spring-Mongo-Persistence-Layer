package org.manfer.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manfer.dto.PlayerDto;
import org.manfer.persistence.repositories.MongoDtoRepository;
import org.manfer.persistence.spring.PersistenceContextConfiguration;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



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
    public void shouldTestTheCallsToSoreAPlayerUsingTheRegisteredMongoDtoRepository() {
        PlayerDto player = mock(PlayerDto.class);
        Object mongoRepositoryType = mock(Object.class);
        Object dtoType = mock(Object.class);
        MongoDtoRepository mock_mongoDtoRepository = mock(MongoDtoRepository.class);
        Class dtoTypeClass = dtoType.getClass();
        Class mongoRepositoryClass = mongoRepositoryType.getClass();
        
        when(mock_map.get(any(Object.class))).thenReturn(mongoRepositoryClass);  
        when(mock_repoFactory.getRepository(mongoRepositoryClass)).thenReturn(mock_mongoDtoRepository);
        
        persistence.save(player);
        
        verify(mock_repoFactory).getRepository(mongoRepositoryType.getClass());
        verify(mock_mongoDtoRepository).save(player);
    }

    
    

//    @Test
//    public void shouldPersistAndLoadAListOfPlayers() {
//        List<PlayerDto> lstPlayers = saveSampleListOfPlayers();
//        
//        List<PlayerDto> loadedListPlayers = loadAllCurrentList();
//        assertTrue(loadedListPlayers.size() == lstPlayers.size());
//    }
//
//    private List<PlayerDto> saveSampleListOfPlayers() {
//        List<PlayerDto> lstPlayers = getSampleListOfPlayers();
//        persistence.saveAll(lstPlayers);
//        return lstPlayers;
//    }
//
//    private List<PlayerDto> getSampleListOfPlayers() {
//        List<PlayerDto> lstPlayers = new ArrayList<>();
//        lstPlayers.add(createPlayerDto("Xabi", "Hernandez"));
//        lstPlayers.add(createPlayerDto("Xabi", "Busquets"));
//        lstPlayers.add(createPlayerDto("Oleguer", "Presas"));
//        return lstPlayers;
//    }
//
//
//
//    @Test
//    public void shouldDeleteOnePlayer() {
//        List<PlayerDto> lstPlayers = saveSampleListOfPlayers();
//        PlayerDto playerToDelete = lstPlayers.get(0);
//
//        persistence.delete(playerToDelete, PlayerDto.class);
//        
//        List<PlayerDto> loadedListAfterDelete = loadAllCurrentList();
//        assertTrue(loadedListAfterDelete.size() == lstPlayers.size()-1);
//    }
//
//    private List<PlayerDto> loadAllCurrentList() {
//        return persistence.findAll(PlayerDto.class);
//    }
//
//    @Test
//    public void shouldDeleteAllPlayerDtoCollection() {
//        persistence.deleteAll(PlayerDto.class);
//        
//        List<PlayerDto> loadedListAfterDelete = loadAllCurrentList();
//        assertTrue(loadedListAfterDelete.isEmpty());        
//    }
    

}
