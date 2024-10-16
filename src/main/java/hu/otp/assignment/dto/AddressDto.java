package hu.otp.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record AddressDto(

        @Min(value = 1000, message = "Zip codes are supposed to be between 1000 and 9985")
        @Max(value = 9985, message = "Zip codes are supposed to be between 1000 and 9985") Integer zipCode,
        @NotBlank(message = "City name must be provided") String city,
        String street,          //a település szintű lakcímekre tekintettel tudatosan lehet null
        String houseNumber,     //a település szintű lakcímekre tekintettel tudatosan lehet null
        @NotNull(message = "The type of address has to be indicated") Boolean isPermanent
) {
}
