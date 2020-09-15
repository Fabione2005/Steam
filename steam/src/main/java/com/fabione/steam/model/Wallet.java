package com.fabione.steam.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "wallet")
public class Wallet {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="amount")
	private double moneyAmount;
	
	@Column(name="lastTimeMoneySpent")
	private LocalDateTime lastTimeMoneySpent;
	
	@Column(name="lastTimeMoneyCharged")
	private LocalDateTime lastTimeMoneyCharged;
	
	@OneToOne(mappedBy = "wallet")
	@JsonIgnore
	private Player player;

	public Wallet() {
		super();
	}

	public Wallet(double moneyAmount, LocalDateTime lastTimeMoneySpent, LocalDateTime lastTimeMoneyCharged) {
		super();
		this.moneyAmount = moneyAmount;
		this.lastTimeMoneySpent = lastTimeMoneySpent;
		this.lastTimeMoneyCharged = lastTimeMoneyCharged;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public LocalDateTime getLastTimeMoneySpent() {
		return lastTimeMoneySpent;
	}

	public void setLastTimeMoneySpent(LocalDateTime lastTimeMoneySpent) {
		this.lastTimeMoneySpent = lastTimeMoneySpent;
	}

	public LocalDateTime getLastTimeMoneyCharged() {
		return lastTimeMoneyCharged;
	}

	public void setLastTimeMoneyCharged(LocalDateTime lastTimeMoneyCharged) {
		this.lastTimeMoneyCharged = lastTimeMoneyCharged;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	} 	
	
}
