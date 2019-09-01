package ch.shades.demo.springit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

	// Just some queries defined by the programmer
	Link findByTitle(String title);
	List<Link> findAllByTitleLikeOrderByCreationDateDesc(String title);
}
