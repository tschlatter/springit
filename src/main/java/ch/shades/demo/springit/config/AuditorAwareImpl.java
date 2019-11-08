package ch.shades.demo.springit.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ch.shades.demo.springit.domain.User;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
			return Optional.of("master@gmail.com");
		}
		
		return Optional.of(((User) authentication.getPrincipal()).getEmail());
	}

	
}
