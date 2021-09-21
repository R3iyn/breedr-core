package eu.breedr.breedrcore.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAuthenticationResponseDto {

    private final String jwt;
}
