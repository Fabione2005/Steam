package com.fabione.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabione.steam.model.Player;
import com.fabione.steam.model.generic.ResponsePlayerWrapper;
import com.fabione.steam.service.PlayerService;

@RestController
public class SteamController {

	@Autowired
	PlayerService service;
	
	@GetMapping(value="players",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponsePlayerWrapper> retrivePlayers() {
		return service.retrieveAllPlayers();
	}
	
	@GetMapping(value="players/age/{age}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponsePlayerWrapper> retrivePlayersOlderThan(@PathVariable int age) {
		return service.retrievePlayersOlderThan(age);
	}
	
	@PostMapping(value="player",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUsers(@RequestBody Player player) {		
		return service.createPlayer(player);
	}
	
	@DeleteMapping(value = "/player/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return service.deleteGamer(id);
	}
	
}
