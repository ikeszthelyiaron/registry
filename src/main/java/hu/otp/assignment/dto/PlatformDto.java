package hu.otp.assignment.dto;

import hu.otp.assignment.domain.Platform;
import jakarta.validation.constraints.NotNull;

public record PlatformDto(
        @NotNull Platform platform
) {
}
