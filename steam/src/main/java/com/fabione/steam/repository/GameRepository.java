package com.fabione.steam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabione.steam.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>
{
	Optional<Game> findByName(String name);
	
}
