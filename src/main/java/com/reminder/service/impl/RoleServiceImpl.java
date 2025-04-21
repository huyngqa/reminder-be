package com.reminder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reminder.model.Role;
import com.reminder.repository.RoleRepository;
import com.reminder.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role selectById(int id) {
		// TODO Auto-generated method stub
		return roleRepository.selectById(id);
	}	
}
