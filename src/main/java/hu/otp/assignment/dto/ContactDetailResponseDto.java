package hu.otp.assignment.dto;

import hu.otp.assignment.domain.Platform;

public record ContactDetailResponseDto(
        Platform platform,
        String identifier
) {
}
