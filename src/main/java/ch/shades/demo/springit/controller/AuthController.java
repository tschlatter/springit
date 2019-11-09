package ch.shades.demo.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.shades.demo.springit.service.UserService;

@Controller
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "auth/profile";
	}
	
	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}
}
