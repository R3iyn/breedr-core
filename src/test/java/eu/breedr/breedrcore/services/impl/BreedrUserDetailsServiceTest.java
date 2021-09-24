package eu.breedr.breedrcore.services.impl;

import eu.breedr.breedrcore.domain.User;
import eu.breedr.breedrcore.domain.UserRole;
import eu.breedr.breedrcore.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BreedrUserDetailsServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private BreedrUserDetailsService breedrUserDetailsService;

    @Test
    public void testLoadByUsername() {
        String username = "user@email.com";

        User user = mock(User.class);
        when(userService.findUser(username)).thenReturn(user);
        when(user.getEmail()).thenReturn(username);
        when(user.getPassword()).thenReturn("password");
        when(user.getActive()).thenReturn(true);

        UserRole userRole = mock(UserRole.class);
        when(user.getRoles()).thenReturn(Collections.singleton(userRole));
        when(userRole.getRole()).thenReturn("ROLE_USER");

        UserDetails userDetails = breedrUserDetailsService.loadUserByUsername(username);

        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getPassword()).isEqualTo("password");
        assertThat(userDetails.isEnabled()).isTrue();
        assertThat(userDetails.isAccountNonExpired()).isTrue();
        assertThat(userDetails.isAccountNonLocked()).isTrue();
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
        assertThat(userDetails.getAuthorities()).hasSize(1);
        assertThat(userDetails.getAuthorities().iterator().next()).isInstanceOf(SimpleGrantedAuthority.class);
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_USER");
    }
}