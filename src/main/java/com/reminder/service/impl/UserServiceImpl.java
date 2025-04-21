package com.reminder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reminder.model.Role;
import com.reminder.model.User;
import com.reminder.model.UserRole;
import com.reminder.repository.RoleRepository;
import com.reminder.repository.UserRepository;
import com.reminder.repository.UserRoleRepository;
import com.reminder.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User selectById(int userId) {
		// TODO Auto-generated method stub
		return userRepository.selectById(userId);
	}

	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		return userRepository.selectAllUser();
	}

	@Transactional
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			int result = userRepository.addUser(user);
		    if (result == 0) {
		        return 0;
		    }
			
		    Role defaultRole = roleRepository.selectByRoleName("ROLE_USER")
		            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
		    UserRole userRole = new UserRole(user.getId(), defaultRole.getId());
		    int roleResult = userRoleRepository.insertUserRole(userRole);
            if (roleResult == 0) {
                throw new RuntimeException("Error: Failed to assign default role.");
            }
            return result;
		} catch (Exception e) {
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }
	}

	@Override
	public int updateUserById(User user) {
		// TODO Auto-generated method stub
		return userRepository.updateUserById(user);
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.deleteUserById(id);
	}

	@Override
	public User selectUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.selectUserByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return !(userRepository.existsByEmail(email) == null);
	}

}
