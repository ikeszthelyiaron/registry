package hu.otp.assignment.dto;

import hu.otp.assignment.domain.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ContactDetailDto(
        @NotNull Platform platform,
        @NotBlank
        @Size(min = 5, message = "The identifier has to be at least 5 characters long") String identifier,
        @Positive(message = "Person ID has to be a positive number") Long personId
        ) {
}
