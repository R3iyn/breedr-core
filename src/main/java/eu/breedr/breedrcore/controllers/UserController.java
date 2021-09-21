package eu.breedr.breedrcore.controllers;

import eu.breedr.breedrcore.dto.user.UserAuthenticationRequestDto;
import eu.breedr.breedrcore.dto.user.UserAuthenticationResponseDto;
import eu.breedr.breedrcore.dto.user.UserInfoDto;
import eu.breedr.breedrcore.dto.user.UserRegistrationDto;
import eu.breedr.breedrcore.services.UserService;
import eu.breedr.breedrcore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UserController(final UserService userService, final AuthenticationManager authenticationManager, @Qualifier("breedrUserDetailsService") final UserDetailsService userDetailsService, final JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @ResponseBody
    public UserInfoDto register(final @RequestBody @Valid UserRegistrationDto userRegistrationDto, final Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation of login has failed");
        }

        return userService.register(userRegistrationDto);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthenticationRequestDto userAuthenticationRequestDto, final Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException("Validation of login has failed");
        }

        // authenticate, will throw BadCredentialsException if authentication fails
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticationRequestDto.getUsername(), userAuthenticationRequestDto.getPassword()));

        // create jwt token and return
        final String jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(userAuthenticationRequestDto.getUsername()));
        return ResponseEntity.ok(new UserAuthenticationResponseDto(jwt));
    }
}
