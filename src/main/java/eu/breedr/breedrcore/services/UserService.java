package eu.breedr.breedrcore.services;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.dto.user.UserInfoDto;
import eu.breedr.breedrcore.dto.user.UserRegistrationDto;

/**
 * Service for handling all interactions with {@link eu.breedr.breedrcore.domain.User} objects in the database
 */
public interface UserService {

    /**
     * Registers a new user
     *
     * @param user the user to register
     */
    UserInfoDto register(UserRegistrationDto user);

    /**
     * Find a user by their username
     *
     * @param username the username
     * @return the {@link User}
     */
    User findUser(String username);
}
