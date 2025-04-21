package com.reminder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reminder.model.UserRole;
import com.reminder.repository.UserRoleRepository;
import com.reminder.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserRole> selectByUserId(int userId) {
		// TODO Auto-generated method stub
		return userRoleRepository.selectByUserId(userId);
	}

	@Override
	public List<UserRole> selectByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return userRoleRepository.selectByUserId(roleId);
	}

}
