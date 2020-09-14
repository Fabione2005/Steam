package com.fabione.steam.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fabione.steam.repository.PlayerRepository;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private PlayerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        
//    	Player playerNew = new Player();
//    	
//    	Game game = new Game();
//    	
//    	game.setName("Resident Evil");
//    	game.setPrice(225.4);
//    	game.setWeightOnGB(25);
//    	
//    	Game game2 = new Game();
//    	
//    	game2.setName("Fortnite");
//    	game2.setPrice(34.4);
//    	game2.setWeightOnGB(45);
//    	
//    	List<Game> games = new ArrayList<>();
//    	games.add(game);
//    	games.add(game2);
//    	
//    	Set<Game> gamesList = new HashSet<>(games);
//    	
//    	playerNew.setName("Fabio");
//    	playerNew.setLastName("Vecchio");
//    	playerNew.setEmail("elmo@gmail.com");
//    	playerNew.setCreated(LocalDateTime.now());
//    	playerNew.setAge(22);
//    	
//    	playerNew.setGames(gamesList);
//    	
//    	repository.save(playerNew);
    	
    	
    }
}