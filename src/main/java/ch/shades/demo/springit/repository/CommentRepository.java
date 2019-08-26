package ch.shades.demo.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
