package ch.shades.demo.springit.service;

import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.Comment;
import ch.shades.demo.springit.repository.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

}
