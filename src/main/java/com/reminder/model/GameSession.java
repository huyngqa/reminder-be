package com.reminder.model;

import java.time.LocalDateTime;

public class GameSession {
	private Integer id;
	private String gameName;
	private LocalDateTime createAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public GameSession(Integer id, String gameName, LocalDateTime createAt) {
		this.id = id;
		this.gameName = gameName;
		this.createAt = createAt;
	}
	
	
}
