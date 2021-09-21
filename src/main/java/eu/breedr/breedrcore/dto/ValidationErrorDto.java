package eu.breedr.breedrcore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationErrorDto {

    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public void addFieldError(String path, String message) {
        FieldErrorDto error = new FieldErrorDto(path, message);
        fieldErrors.add(error);
    }
}
