package com.reminder.service;

import java.util.List;

import com.reminder.model.UserRole;

public interface UserRoleService {

	List<UserRole> selectByUserId(int userId);

	List<UserRole> selectByRoleId(int roleId);
}
