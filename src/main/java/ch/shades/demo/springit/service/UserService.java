package ch.shades.demo.springit.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.User;
import ch.shades.demo.springit.repository.UserRepository;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	
	private final RoleService roleService;
	private final MailService mailService;
	
	public UserService(UserRepository userRepository, RoleService roleService, MailService mailService) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.mailService = mailService;
		
		// Better create a bean with encoder and use autowiring. For being simple, we just instanciate it here.
		this.encoder = new BCryptPasswordEncoder();
	}
	
	public User register(User user) {
		// Take the password from the form and encode
		String secret = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(secret);
		
		// confirm password
		user.setConfirmPassword(secret); 			// We can set it to the same secret because the confirmPassword has been checked already. TS: Das gefällt mir gar nicht
		
		// Assign a role to this user
		user.addRole(roleService.findByName("ROLE_USER"));
		
		// set an activation code
		user.setActivationCode(UUID.randomUUID().toString());
		
		// disable the user because he is not activated yet
		user.setEnabled(false);
		
		// save the user
		save(user);
		
		// send the activation email
		sendActivationEmail(user);
		
		// return the user
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void sendActivationEmail(User user) {
		mailService.sendActivationEmail(user);
	}
	
	public void sendWelcomeEmail(User user) {
		mailService.sendWelcomeEmail(user);
	}
	
	public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
		return userRepository.findByEmailAndActivationCode(email, activationCode);
	}
}
