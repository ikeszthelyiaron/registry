package hu.otp.assignment.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PersonResponseDto(
        String name,
        AddressDto permanent,
        AddressDto temporary,
        List<ContactDetailResponseDto> contactDetails
) {
}
