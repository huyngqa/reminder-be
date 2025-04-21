package com.reminder.service.impl;

import com.reminder.model.User;
import com.reminder.repository.UserRepository;
import com.reminder.repository.UserRoleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //check "ten dang nhap" is exits
        User user = userRepository.selectUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản: " + email + " không tồn tại");
        }
        
        List<String> roleNames = userRoleRepository.getRoleNamesByUserId(user.getId());
        
        return UserDetailsImpl.build(user, roleNames);
    }
}
