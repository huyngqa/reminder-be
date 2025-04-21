package com.reminder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.reminder.repository")
public class ReminderBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderBeApplication.class, args);
	}

}
