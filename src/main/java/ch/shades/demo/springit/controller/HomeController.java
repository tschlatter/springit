package ch.shades.demo.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

// Removed the following because we have an index.html in ../resources/static
//	@RequestMapping("/")
//	public String home() {
//		return "Hello Spring Boot 2!";
//	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Hello Thymeleaf!");
		return "home";
	}
}
