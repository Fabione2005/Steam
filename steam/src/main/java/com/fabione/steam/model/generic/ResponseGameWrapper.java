package com.fabione.steam.model.generic;

import java.util.Set;

import com.fabione.steam.model.Game;

public class ResponseGameWrapper {

	private Set<Game> Games;

	public Set<Game> getGame() {
		return Games;
	}

	public void setGame(Set<Game> Games) {
		this.Games = Games;
	}
	
}
