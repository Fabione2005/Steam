package com.fabione.steam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "game")
public class Game {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="weight_on_gb")
	private double weightOnGB;
	
	@Column(name="price")
	private double price;
	
	//@ManyToMany(mappedBy = "games")
	@ManyToMany()
	@JoinTable(name = "library_user_game",
	joinColumns = { @JoinColumn(name = "game_id")},
	inverseJoinColumns = { @JoinColumn (name = "player_id")})
	@JsonIgnore
	private Set<Player> users = new HashSet<>();
	
	public Game() {
		super();
	}
	
	public Game(String name, double weightOnGB, double price, Set<Player> users) {
		super();
		this.name = name;
		this.weightOnGB = weightOnGB;
		this.price = price;
		//this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeightOnGB() {
		return weightOnGB;
	}

	public void setWeightOnGB(double weightOnGB) {
		this.weightOnGB = weightOnGB;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public Set<Player> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<Player> users) {
//		this.users = users;
//	}
	
}
