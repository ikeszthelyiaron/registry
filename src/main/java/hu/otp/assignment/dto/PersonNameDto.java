package hu.otp.assignment.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonNameDto(
        @NotBlank(message = "Name cannot be blank") String name
) {
}
