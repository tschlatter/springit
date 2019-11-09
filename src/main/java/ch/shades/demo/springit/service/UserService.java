package ch.shades.demo.springit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.User;
import ch.shades.demo.springit.repository.UserRepository;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User register(User user) {
		
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	

}
