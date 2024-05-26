package com.koped.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koped.model.User;
import com.koped.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
        if (!(user == null)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("User")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
	}

	@Override
	public User createUser(String username, String password, String email) {
		User usernameExist = userRepo.findByUsername(username);
		User emailExist = userRepo.findByEmail(email);
		
		if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Please fill all the form");
        } else if (emailExist != null) {
            throw new IllegalArgumentException("Email already exist");
        } else if (usernameExist != null) {
            throw new IllegalArgumentException("Username already exist");
        } else {
            User user = new User();

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRole("User");

            return userRepo.save(user);
        }
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			return null;
		}
		else {
			return user;
		}
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
