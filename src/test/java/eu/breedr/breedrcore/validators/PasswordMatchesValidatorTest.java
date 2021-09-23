package eu.breedr.breedrcore.validators;

import eu.breedr.breedrcore.dto.user.UserRegistrationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordMatchesValidatorTest {

    @InjectMocks
    private PasswordMatchesValidator passwordMatchesValidator;

    @Test
    public void testIsValid_valid() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);
        when(userRegistrationDto.getPassword()).thenReturn("password");
        when(userRegistrationDto.getMatchingPassword()).thenReturn("password");

        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = passwordMatchesValidator.isValid(userRegistrationDto, constraintValidatorContext);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsValid_invalid() {
        UserRegistrationDto userRegistrationDto = mock(UserRegistrationDto.class);
        when(userRegistrationDto.getPassword()).thenReturn("password");
        when(userRegistrationDto.getMatchingPassword()).thenReturn("other");

        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = passwordMatchesValidator.isValid(userRegistrationDto, constraintValidatorContext);

        assertThat(result).isFalse();
    }
}