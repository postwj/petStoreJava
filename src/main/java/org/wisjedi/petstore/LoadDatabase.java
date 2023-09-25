package org.wisjedi.petstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wisjedi.petstore.infrastructure.UserRepository;
import org.wisjedi.petstore.model.User;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("samurai", "Johny", "Silverhand")));
            log.info("Preloading " + repository.save(new User("Spectr", "Jane", "Sheppard")));
        };
    }
}
