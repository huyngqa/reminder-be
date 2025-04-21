package com.reminder.model;

import java.time.LocalDateTime;

public class Round {
	private Long id;
	private int roundNumber;
	private LocalDateTime createdAt;
	private String roundDetails;
	
	public Round(Long id, int roundNumber, LocalDateTime createdAt, String roundDetails) {
		this.id = id;
		this.roundNumber = roundNumber;
		this.createdAt = createdAt;
		this.roundDetails = roundDetails;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getRoundDetails() {
		return roundDetails;
	}
	public void setRoundDetails(String roundDetails) {
		this.roundDetails = roundDetails;
	}
}
