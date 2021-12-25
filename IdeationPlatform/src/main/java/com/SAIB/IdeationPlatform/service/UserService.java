package com.SAIB.IdeationPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAIB.IdeationPlatform.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
}
