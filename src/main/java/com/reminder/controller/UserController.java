package com.reminder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reminder.model.User;
import com.reminder.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.selectAllUser(), HttpStatus.OK);
    }
}
