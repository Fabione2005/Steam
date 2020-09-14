package com.fabione.steam.model.generic;

import java.util.Set;

import com.fabione.steam.model.Player;

public class ResponsePlayerWrapper {

	private Set<Player> players;

	public Set<Player> getPlayer() {
		return players;
	}

	public void setPlayer(Set<Player> players) {
		this.players = players;
	}
	
}
