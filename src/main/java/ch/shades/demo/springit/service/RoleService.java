package ch.shades.demo.springit.service;

import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.Role;
import ch.shades.demo.springit.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
