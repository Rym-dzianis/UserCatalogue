package com.epam.rymasheuski.userCatalogue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UserCatalogueApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCatalogueApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(UserCatalogueApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			LOGGER.info("Beans from Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();

			//Stream.of(beanNames).filter(s -> s.contains("org.springframework.boot")).sorted().forEach(LOGGER::info);
		};
	}
}

