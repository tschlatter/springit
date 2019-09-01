package ch.shades.demo.springit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ch.shades.demo.springit.config.SpringitProperties;
import ch.shades.demo.springit.domain.Comment;
import ch.shades.demo.springit.domain.Link;
import ch.shades.demo.springit.repository.CommentRepository;
import ch.shades.demo.springit.repository.LinkRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);
	
	@Autowired
	private SpringitProperties springitProperties; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	@Bean
	@Profile("dev")
	@Order(1)
	CommandLineRunner runner() {
		return args -> {
			System.out.println("This is only to be shown on DEV: Welcome message: " + springitProperties.getWelcomeMsg());
			
			log.debug("This is a debug log message");
		};
	}

	@Bean
	@Order(2)
	CommandLineRunner dbrunner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			Link link = new Link();
			link.setTitle("Getting started with Spring Boot 2");
			link.setUrl("https://therealdanvega.com/spring-boot-2");
			linkRepository.save(link);
			
			Comment comment = new Comment();
			comment.setBody("This Spring Boot 2 link is awesome!");
			comment.setLink(link);
			commentRepository.save(comment);
			
			link.addComment(comment);
			
			log.debug("Inserted a Link and a Comment.");
			
			// Test
			Link firstLink = linkRepository.findByTitle("Getting started with Spring Boot 2");
			log.debug("Found Title: " + firstLink.getTitle());
			
			//List<Link> links = linkRepository.findAllByTitleLikeOrderByCreationDateDesc("Spring Boot");
		};
	}
}
