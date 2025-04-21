package com.reminder.ulti;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.reminder.dto.GameDetailsDto;
import com.reminder.model.GameSession;
import com.reminder.model.Round;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void demoPoint() {
		GameSession gameSession = new GameSession(1, "tien len", LocalDateTime.now());
		GameDetailsDto gameDetailsDto = new GameDetailsDto();
		List<String> players = new ArrayList();
		List<String> points = new ArrayList();
	
		players.add("Player1");
		players.add("Player2");
		players.add("Player3");
		players.add("Player4");
		
		Round round = new Round(1L, 1, LocalDateTime.now(), "a");
	}

}
