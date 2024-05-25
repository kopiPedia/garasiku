package com.koped.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.koped.model.User;

public interface UserService extends UserDetailsService {
	
	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	User createUser(String username, String password, String email);
	User findByUsername(String username);
	User editUser(User user);

}
