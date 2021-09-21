package eu.breedr.breedrcore.services;

import eu.breedr.breedrcore.dto.UserInfoDto;
import eu.breedr.breedrcore.dto.UserRegistrationDto;

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
}
