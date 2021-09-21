package eu.breedr.breedrcore.controllers;

import eu.breedr.breedrcore.dto.UserInfoDto;
import eu.breedr.breedrcore.dto.UserRegistrationDto;
import eu.breedr.breedrcore.services.UserService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public UserInfoDto register(final @RequestBody @Valid UserRegistrationDto userRegistrationDto, final Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation of register form failed");
        }

        return userService.register(userRegistrationDto);
    }
}
