package eu.breedr.breedrcore.services.impl;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.domain.UserRole;
import eu.breedr.breedrcore.dto.user.UserInfoDto;
import eu.breedr.breedrcore.dto.user.UserRegistrationDto;
import eu.breedr.breedrcore.exceptions.UserAlreadyExistsException;
import eu.breedr.breedrcore.repositories.UserRepository;
import eu.breedr.breedrcore.repositories.UserRoleRepository;
import eu.breedr.breedrcore.utils.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private MapUtils mapUtils;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterWhenUserAlreadyExists() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);
        when(userRegistrationDto.getEmail()).thenReturn("username");

        User user = mock(User.class);
        when(userRepository.findByEmail("username")).thenReturn(user);

        userService.register(userRegistrationDto);
    }

    @Test
    public void testRegister() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);
        when(userRegistrationDto.getPassword()).thenReturn("password");

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("password");
        when(mapUtils.mapObject(userRegistrationDto, User.class)).thenReturn(user);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        UserRole userRole = mock(UserRole.class);
        when(userRoleRepository.findUserRoleByRole("USER")).thenReturn(userRole);

        UserInfoDto userInfoDto = mock(UserInfoDto.class);
        when(mapUtils.mapObject(user, UserInfoDto.class)).thenReturn(userInfoDto);

        UserInfoDto result = userService.register(userRegistrationDto);

        assertThat(result).isEqualTo(userInfoDto);

        verify(user).setPassword("encodedPassword");
        verify(user).setRoles(Collections.singleton(userRole));
        verify(user).setActive(true);

        verify(userRepository).save(user);
    }

    @Test
    public void testFindUser() {
        String username = "username";

        User user = mock(User.class);
        when(userRepository.findByEmail(username)).thenReturn(user);

        User result = userService.findUser(username);

        assertThat(result).isEqualTo(user);
    }
}