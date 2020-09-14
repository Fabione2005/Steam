package com.fabione.steam.service;

import org.springframework.http.ResponseEntity;

import com.fabione.steam.model.Player;
import com.fabione.steam.model.generic.ResponsePlayerWrapper;

public interface PlayerService {

	ResponseEntity<String> createPlayer(Player player);
	ResponseEntity<String> deleteGamer(Long playerId);
	ResponseEntity<ResponsePlayerWrapper> retrieveAllPlayers();
	ResponseEntity<ResponsePlayerWrapper> retrievePlayersOlderThan(int age);
	
}
