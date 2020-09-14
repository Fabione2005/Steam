package com.fabione.steam.service;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fabione.steam.model.Player;
import com.fabione.steam.model.generic.ResponsePlayerWrapper;
import com.fabione.steam.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository repository;
	
	@Autowired
	private EmailValidatorService emailValidator;
	
	@Autowired
	private AgeValidatorService ageValidator;

	@Override
	public ResponseEntity<String> createPlayer(Player user) {
		
		emailValidator.validateEmail(user.getEmail());
		
		ageValidator.AgeMinValidator(user.getAge());
		
		user.setCreated(LocalDateTime.now());
		
		repository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("player created");
	}

	@Override
	public ResponseEntity<ResponsePlayerWrapper> retrieveAllPlayers() {
		
		ResponsePlayerWrapper responseBody = new ResponsePlayerWrapper();
		
		responseBody.setPlayer(new HashSet<>(repository.findAll()));
		
		return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
	}

	@Override
	public ResponseEntity<String> deleteGamer(Long playerId) {
	
		Player playerFound = repository.findById(playerId).get();
		
		repository.delete(playerFound);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("player deleted");
	}

	@Override
	public ResponseEntity<ResponsePlayerWrapper> retrievePlayersOlderThan(int age) {
		
		ResponsePlayerWrapper responseBody = new ResponsePlayerWrapper();
		
		responseBody.setPlayer(new HashSet<>(repository.getPlayerOrderThanProcedure(age)));
		
		return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
	}
	
}
