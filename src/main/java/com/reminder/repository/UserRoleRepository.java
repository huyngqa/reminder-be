package com.reminder.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.reminder.model.UserRole;

@Mapper
public interface UserRoleRepository {

    List<UserRole> selectByUserId(int userId);
    
    List<UserRole> selectByRoleId(int roleId);
    
    List<String> getRoleNamesByUserId(int userId);
    
    int insertUserRole(UserRole userRole);
}
