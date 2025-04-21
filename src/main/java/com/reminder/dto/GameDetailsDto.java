package com.reminder.dto;

import java.util.List;

public class GameDetailsDto {
	private List<String> players;
	private List<Integer> points;
	
	public GameDetailsDto() {
	}
	
	public GameDetailsDto(List<String> players, List<Integer> points) {
		super();
		this.players = players;
		this.points = points;
	}
	public List<String> getPlayers() {
		return players;
	}
	public void setPlayers(List<String> players) {
		this.players = players;
	}
	public List<Integer> getPoints() {
		return points;
	}
	public void setPoints(List<Integer> points) {
		this.points = points;
	}
}
