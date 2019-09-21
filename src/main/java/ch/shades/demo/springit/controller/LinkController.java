package ch.shades.demo.springit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.shades.demo.springit.domain.Link;
import ch.shades.demo.springit.repository.LinkRepository;

@RestController
@RequestMapping("/links")
public class LinkController {
	private LinkRepository linkRepository;
	
	public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	// List
	@GetMapping("/")
	public List<Link> list(){
		return linkRepository.findAll();
	}
	
	// CRUD
	
	@PostMapping("/create")
	public Link create(@ModelAttribute Link link) {
		return linkRepository.save(link);
	}
	
	@GetMapping("/{id}")
	public Optional<Link> read(@PathVariable Long id) {
		return linkRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	public Link update(@PathVariable Long id, @ModelAttribute Link link) {
		//TODO: Get the id
		return linkRepository.save(link);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		linkRepository.deleteById(id);
	}
}
