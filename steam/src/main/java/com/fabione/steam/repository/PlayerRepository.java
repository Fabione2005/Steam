package com.fabione.steam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fabione.steam.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Query(value = "select * from getplayersolderthan(:ageIn)", nativeQuery = true)
	List<Player> getPlayerOrderThanProcedure(@Param("ageIn") int ageIn);

}
