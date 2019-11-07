package ch.shades.demo.springit;

import org.ocpsoft.prettytime.PrettyTime;
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
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import ch.shades.demo.springit.config.SpringitProperties;

@SpringBootApplication
//@EnableJpaAuditing Removed it because we start using JpaConfig with enabled Jpa Auditing, so we can use more fields for auditing
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);
	
	@Autowired
	private SpringitProperties springitProperties; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	// Here we create a Bean so we don't have to instantiate PrettyTime on every Instantiation of Link
	@Bean
	PrettyTime prettyTime() {
		return new PrettyTime();
	}
	
	//////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////
	// TODO * Configuring this bean should not be needed once Spring Boot's Thymeleaf starter includes configuration
	// TODO   for thymeleaf-extras-springsecurity5 (instead of thymeleaf-extras-springsecurity4)
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
	
	
	// Some stuff we did while the course was ongoing but we don't need it anymore
	
	@Bean
	@Profile("dev")
	@Order(1)
	CommandLineRunner runner() {
		return args -> {
			System.out.println("This is only to be shown on DEV: Welcome message: " + springitProperties.getWelcomeMsg());
			
			log.debug("This is a debug log message");
		};
	}

//	@Bean
//	@Order(2)
//	CommandLineRunner dbrunner(LinkRepository linkRepository, CommentRepository commentRepository) {
//		return args -> {
//			Link link = new Link();
//			link.setTitle("Getting started with Spring Boot 2");
//			link.setUrl("https://therealdanvega.com/spring-boot-2");
//			linkRepository.save(link);
//			
//			Comment comment = new Comment();
//			comment.setBody("This Spring Boot 2 link is awesome!");
//			comment.setLink(link);
//			commentRepository.save(comment);
//			
//			link.addComment(comment);
//			
//			log.debug("Inserted a Link and a Comment.");
//			
//			// Test
//			Link firstLink = linkRepository.findByTitle("Getting started with Spring Boot 2");
//			log.debug("Found Title: " + firstLink.getTitle());
//			
//			//List<Link> links = linkRepository.findAllByTitleLikeOrderByCreationDateDesc("Spring Boot");
//		};
//	}
}
