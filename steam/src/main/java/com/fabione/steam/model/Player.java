package com.fabione.steam.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "player")
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="age")
	private int age;
	
	@Column(name="created")
	private LocalDateTime created;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "library_user_game",
		joinColumns = { @JoinColumn(name = "player_id")},
		inverseJoinColumns = { @JoinColumn (name = "game_id")})
	@JsonProperty(access = Access.READ_ONLY)
	private Set<Game> games = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonProperty(access = Access.READ_ONLY)
	private Wallet wallet;

	public Player() {
		super();
	}

	public Player(String name, String lastName, String email, int age, LocalDateTime created, Set<Game> games) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.created = created;
		this.games = games;
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


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public Set<Game> getGames() {
		return games;
	}


	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
}
