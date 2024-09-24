package com.task.cc.service;



import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.cc.model.User;
import com.task.cc.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository; 
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User userInfo = userRepository.getUserInfoByUsername(username);

	    if (userInfo == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }

	    return new org.springframework.security.core.userdetails.User(
	            userInfo.getUsername(),
	            userInfo.getPassword(),
	            Collections.emptyList()  
	    );
	}

	public User saveUser(User user) {
        return userRepository.save(user);
    }

}

