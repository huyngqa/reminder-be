package com.reminder.model;

import java.time.Instant;

public class RefreshToken {
    private Integer id;
    private Integer userId;
    private String token;
    private Instant expiryDate;
    
    public RefreshToken() {
	}
    
	public RefreshToken(Integer id, Integer userId, String token, Instant expiryDate) {
		this.id = id;
		this.userId = userId;
		this.token = token;
		this.expiryDate = expiryDate;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Instant getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    
}
