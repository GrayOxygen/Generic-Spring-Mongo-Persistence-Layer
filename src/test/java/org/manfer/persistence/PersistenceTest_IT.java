package org.manfer.persistence;

import org.manfer.persistence.spring.PersistenceContextConfiguration;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manfer.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



//TODO: Improve test and add negative path tests.

//TODO: How to work with custom repositories? To find by name for example.

/**
 * Unit tests for the main interface of the persistence layer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContextConfiguration.class})
public class PersistenceTest_IT {

    @Autowired
    private Persistence persistence;

    @Test
    public void shouldTestConnectionToMongoDB() {
        boolean connected = persistence.isConnected();
        assertTrue(connected);
    }

    @Test
    public void shouldPersistAPlayerDto() {
        PlayerDto player = createPlayerDto("Leo", "Messi");
        persistence.save(player);
    }

    private PlayerDto createPlayerDto(String name, String surname) {
        PlayerDto player = new PlayerDto(name, surname);
        player.setFaults_Committed(3);
        player.setFaults_Received(10);
        player.setScored_Goals(1000);
        return player;
    }

    @Test
    public void shouldPersistAListOfPlayers() {
        List<PlayerDto> lstPlayers = new ArrayList<>();
        lstPlayers.add(createPlayerDto("Xabi", "Hernandez"));
        lstPlayers.add(createPlayerDto("Xabi", "Busquets"));
        lstPlayers.add(createPlayerDto("Oleguer", "Presas"));

        persistence.saveAll(lstPlayers);
    }

    @Test
    public void shouldLoadAPlayerByName() {
        
        persistence.findByName("Leo", PlayerDto.class);
        
    }
    
    
    @Test
    public void shouldLoadAPlayerDtoByName() {
        PlayerDto bestPlayer = persistence.findOne("539f149644aed29e03e4cacf", PlayerDto.class);
        assertTrue(bestPlayer.getName().equals("Leo"));
    }

    @Test
    public void shouldLoadAllPlayers() {
        List<PlayerDto> players = persistence.findAll(PlayerDto.class);
        assertTrue(players.size() > 0);
    }

    @Test
    public void shouldDeleteAPlayer() {
        List<PlayerDto> players = persistence.findAll(PlayerDto.class);
        PlayerDto playerToDelete = players.get(0);

        persistence.delete(playerToDelete, PlayerDto.class);
    }

    @Test
    public void shouldDeleteAllPlayerDtoCollection() {
        persistence.deleteAll(PlayerDto.class);
    }
    

}
