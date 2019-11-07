package ch.shades.demo.springit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
