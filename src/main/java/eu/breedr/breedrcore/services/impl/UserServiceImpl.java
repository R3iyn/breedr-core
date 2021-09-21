package eu.breedr.breedrcore.services.impl;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.dto.UserInfoDto;
import eu.breedr.breedrcore.dto.UserRegistrationDto;
import eu.breedr.breedrcore.exceptions.UserAlreadyExistsException;
import eu.breedr.breedrcore.repositories.UserRepository;
import eu.breedr.breedrcore.repositories.UserRoleRepository;
import eu.breedr.breedrcore.services.UserService;
import eu.breedr.breedrcore.utils.MapUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final MapUtils mapUtils;

    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder, final UserRoleRepository userRoleRepository, final MapUtils mapUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.mapUtils = mapUtils;
    }

    @Override
    public UserInfoDto register(final UserRegistrationDto userRegistrationDto) throws UserAlreadyExistsException {
        if (userAlreadyExists(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistsException("There already is a user with this email");
        }

        User user = mapUtils.mapObject(userRegistrationDto, User.class);

        // Set required fields
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(userRoleRepository.findUserRoleByRole("USER")));
        user.setActive(true);

        userRepository.save(user);

        return mapUtils.mapObject(user, UserInfoDto.class);
    }

    @Override
    public User findUser(final String username) {
        return userRepository.findByEmail(username);
    }

    private boolean userAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
