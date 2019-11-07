package ch.shades.demo.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.shades.demo.springit.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
