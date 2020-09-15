package com.fabione.steam.service.player;

import org.springframework.http.ResponseEntity;

import com.fabione.steam.model.Player;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.ResponsePlayerWrapper;

public interface PlayerService {

	ResponseEntity<BaseResult> createPlayer(Player player);
	ResponseEntity<BaseResult> deletePlayer(Long playerId);
	ResponseEntity<ResponsePlayerWrapper> retrieveAllPlayers();
	ResponseEntity<BaseResult> buyGameToPlayer(Long playerId, Long gameId);
	ResponseEntity<ResponsePlayerWrapper> retrievePlayersOlderThan(int age);
	
	//Wallet Services
	ResponseEntity<BaseResult> chargeMoney(Long playerId, double moneyAmount);
}
