package ch.shades.demo.springit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoaderNotUsed implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Database Loader Example...");
		
	}

}
