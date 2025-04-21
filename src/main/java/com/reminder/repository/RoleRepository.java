package com.reminder.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.reminder.model.Role;

@Mapper
public interface RoleRepository {

	Role selectById(int id);
	
    Optional<Role> selectByRoleName(String roleName);
}
