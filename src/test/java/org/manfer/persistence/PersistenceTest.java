package org.manfer.persistence;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manfer.dto.PlayerDto;
import org.manfer.persistence.spring.PersistenceContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * Unit tests for the main interface of the persistence layer.
 * 
 * @author marcandreuf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContextConfiguration.class})
public class PersistenceTest {
    
    
    

    @Autowired
    private Persistence persistence;
    
    public PersistenceTest(){
        persistence = new PersistenceMongoImpl(null, null);
    }
    

    @Test
    public void shouldTestConnectionToMongoDB() {
        boolean connected = persistence.isConnected();
        assertTrue(connected);
    }
    
    
    @Before
    public void setup(){
        persistence.deleteAll(PlayerDto.class);
    }

    
    @Test
    public void shouldPersistAndLoadAPlayerDtoByItsName() {
        PlayerDto player = createPlayerDto("Leo", "Messi");
        persistence.save(player);
        
        PlayerDto loadedPlayer = persistence.findByName("Leo", PlayerDto.class);
        assertTrue(player.equals(loadedPlayer));
    }

    private PlayerDto createPlayerDto(String name, String surname) {
        PlayerDto player = new PlayerDto(name, surname);
        player.setFaults_Committed(3);
        player.setFaults_Received(10);
        player.setScored_Goals(1000);
        return player;
    }
    
    

    @Test
    public void shouldPersistAndLoadAListOfPlayers() {
        List<PlayerDto> lstPlayers = saveSampleListOfPlayers();
        
        List<PlayerDto> loadedListPlayers = loadAllCurrentList();
        assertTrue(loadedListPlayers.size() == lstPlayers.size());
    }

    private List<PlayerDto> saveSampleListOfPlayers() {
        List<PlayerDto> lstPlayers = getSampleListOfPlayers();
        persistence.saveAll(lstPlayers);
        return lstPlayers;
    }

    private List<PlayerDto> getSampleListOfPlayers() {
        List<PlayerDto> lstPlayers = new ArrayList<>();
        lstPlayers.add(createPlayerDto("Xabi", "Hernandez"));
        lstPlayers.add(createPlayerDto("Xabi", "Busquets"));
        lstPlayers.add(createPlayerDto("Oleguer", "Presas"));
        return lstPlayers;
    }



    @Test
    public void shouldDeleteOnePlayer() {
        List<PlayerDto> lstPlayers = saveSampleListOfPlayers();
        PlayerDto playerToDelete = lstPlayers.get(0);

        persistence.delete(playerToDelete, PlayerDto.class);
        
        List<PlayerDto> loadedListAfterDelete = loadAllCurrentList();
        assertTrue(loadedListAfterDelete.size() == lstPlayers.size()-1);
    }

    private List<PlayerDto> loadAllCurrentList() {
        return persistence.findAll(PlayerDto.class);
    }

    @Test
    public void shouldDeleteAllPlayerDtoCollection() {
        persistence.deleteAll(PlayerDto.class);
        
        List<PlayerDto> loadedListAfterDelete = loadAllCurrentList();
        assertTrue(loadedListAfterDelete.isEmpty());        
    }
    

}
