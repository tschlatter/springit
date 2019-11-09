package ch.shades.demo.springit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.Link;
import ch.shades.demo.springit.repository.LinkRepository;

@Service
public class LinkService {
	
	private final LinkRepository linkRepository;
	
	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
		
	}
	
	public List<Link> findAll() {
		return linkRepository.findAll();
	}
	
	public Optional<Link> findById(Long id) {
		return linkRepository.findById(id);
	}

	public Link save(Link link) {
		return linkRepository.save(link);
	}
}
