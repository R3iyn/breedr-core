package eu.breedr.breedrcore.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

    @InjectMocks
    private EmailValidator emailValidator;

    @Test
    public void testValidateEmail_valid() {
        String email = "test@test.com";
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = emailValidator.isValid(email, constraintValidatorContext);

        assertThat(result).isTrue();
    }

    @Test
    public void testValidateEmail_invalid_1() {
        String email = "invalid@invalid";
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = emailValidator.isValid(email, constraintValidatorContext);

        assertThat(result).isFalse();
    }

    @Test
    public void testValidateEmail_invalid_2() {
        String email = "invalid";
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = emailValidator.isValid(email, constraintValidatorContext);

        assertThat(result).isFalse();
    }

    @Test
    public void testValidateEmail_invalid_3() {
        String email = "invalid.com";
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        boolean result = emailValidator.isValid(email, constraintValidatorContext);

        assertThat(result).isFalse();
    }
}