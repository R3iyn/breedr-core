package eu.breedr.breedrcore.initialdata;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.domain.UserRole;
import eu.breedr.breedrcore.repositories.UserRepository;
import eu.breedr.breedrcore.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TestCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserRole userRole = new UserRole(1, "ROLE_ADMIN");
        userRoleRepository.save(userRole);

        User user = new User();
        user.setActive(true);
        user.setPassword(passwordEncoder.encode("testPassword"));
        user.setEmail("brian.poncelet@breedr.com");
        user.setFirstName("Brian");
        user.setLastName("Poncelet");
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }
}
