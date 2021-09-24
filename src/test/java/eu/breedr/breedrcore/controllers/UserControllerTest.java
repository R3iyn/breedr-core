package eu.breedr.breedrcore.controllers;

import eu.breedr.breedrcore.dto.user.UserAuthenticationRequestDto;
import eu.breedr.breedrcore.dto.user.UserAuthenticationResponseDto;
import eu.breedr.breedrcore.dto.user.UserInfoDto;
import eu.breedr.breedrcore.dto.user.UserRegistrationDto;
import eu.breedr.breedrcore.services.UserService;
import eu.breedr.breedrcore.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

import javax.validation.ValidationException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserController userController;

    @Test
    public void testRegister() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);

        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(false);

        UserInfoDto userInfoDto = mock(UserInfoDto.class);
        when(userController.register(userRegistrationDto, errors)).thenReturn(userInfoDto);

        UserInfoDto result = userController.register(userRegistrationDto, errors);

        assertThat(result).isEqualTo(userInfoDto);
    }

    @Test(expected = ValidationException.class)
    public void testRegisterWhenErrors() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);

        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);

        userController.register(userRegistrationDto, errors);
    }

    @Test
    public void testLogin() {
        UserAuthenticationRequestDto userAuthenticationRequestDto = mock(UserAuthenticationRequestDto.class);
        when(userAuthenticationRequestDto.getUsername()).thenReturn("username");

        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(false);

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);

        when(jwtUtil.generateToken(userDetails)).thenReturn("jwt");

        ResponseEntity<UserAuthenticationResponseDto> result = userController.login(userAuthenticationRequestDto, errors);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isInstanceOf(UserAuthenticationResponseDto.class);
        assertThat(result.getBody().getJwt()).isEqualTo("jwt");
    }

    @Test(expected = ValidationException.class)
    public void testLoginWhenErrors() {
        UserAuthenticationRequestDto userAuthenticationRequestDto = new UserAuthenticationRequestDto();

        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);

        userController.login(userAuthenticationRequestDto, errors);
    }
}