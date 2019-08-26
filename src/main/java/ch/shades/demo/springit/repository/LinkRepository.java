package ch.shades.demo.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
