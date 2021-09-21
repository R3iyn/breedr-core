package eu.breedr.breedrcore.validators;

import eu.breedr.breedrcore.dto.UserRegistrationDto;
import eu.breedr.breedrcore.validators.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistrationDto user = (UserRegistrationDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
