package eu.breedr.breedrcore.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FieldErrorDto {
    private final String field;
    private final String message;
}
