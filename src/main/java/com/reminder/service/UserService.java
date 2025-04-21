package com.reminder.service;

import java.util.List;

import com.reminder.model.User;

public interface UserService {

	User selectById(int userId);
	
    List<User> selectAllUser();
    
    User selectUserByEmail(String email);

    int addUser(User user);
    
    int updateUserById(User user);

    int deleteUserById(int id);
    
    boolean existsByEmail(String email);
}
