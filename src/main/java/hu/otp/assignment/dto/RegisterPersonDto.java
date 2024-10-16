package hu.otp.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterPersonDto(

        @NotBlank
        @Size(min = 3, message = "Name must contain at least three characters") String name,
        @Positive(message = "Permanent Address ID has to be a positive number") Long permanentAddressId,
        Long temporaryAddressId
) {
}
