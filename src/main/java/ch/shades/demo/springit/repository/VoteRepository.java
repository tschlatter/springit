package ch.shades.demo.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
