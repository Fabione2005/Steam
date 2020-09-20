package com.fabione.steam.model.generic;

import java.util.Set;

import com.fabione.steam.model.Game;

public class ResponseGameWrapper {

	private Set<Game> games;
	
	private PageInfoResponse pageInfo;
	
	public ResponseGameWrapper (Set<Game> games) 
	{
		this.games = games;
	}
	
	public ResponseGameWrapper (Set<Game> games,PageInfoResponse pageInfo) 
	{
		this.games = games;
		this.pageInfo = pageInfo;
	}

	public Set<Game> getGame() {
		return games;
	}

	public void setGame(Set<Game> Games) {
		this.games = Games;
	}

	public PageInfoResponse getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfoResponse pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
