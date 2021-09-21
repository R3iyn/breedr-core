package eu.breedr.breedrcore.initialdata;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadUsers {

    private static final Logger log = LoggerFactory.getLogger(LoadUsers.class);

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            log.info("Preloading " + userRepository.save(new User("Bilbo", "Baggins")));
            log.info("Preloading " + userRepository.save(new User("Frodo", "Baggins")));
        };
    }
}
