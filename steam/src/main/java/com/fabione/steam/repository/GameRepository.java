package com.fabione.steam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabione.steam.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>
{
	Optional<Game> findByName(String name);
	List<Game> findByOrderByPriceAsc();
	List<Game> findByOrderByPriceDesc();
	List<Game> findByNameIgnoreCaseContaining(String name);
	Page<Game> findByNameIgnoreCaseContaining(String name,Pageable pageable);
	Page<Game> findAll(Pageable pageable);
}
