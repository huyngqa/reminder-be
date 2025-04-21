package com.reminder.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.reminder.model.User;

@Mapper
public interface UserRepository {

	User selectById(int userId);
	
    List<User> selectAllUser();
    
    User selectUserByEmail(String email);

    int addUser(User user);
    
    int updateUserById(User user);

    int deleteUserById(int id);
    
    String existsByEmail(String email);
}
