package eu.breedr.breedrcore.controlleradvices;

import eu.breedr.breedrcore.dto.ValidationErrorDto;
import eu.breedr.breedrcore.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class UserAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ValidationErrorDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDto dto = new ValidationErrorDto();

        for (FieldError fieldError: fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return dto;
    }
}
