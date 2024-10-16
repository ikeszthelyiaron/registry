package hu.otp.assignment.service;


import hu.otp.assignment.dto.AddressDto;

public interface AddressService {
    void createAddress(AddressDto addressDto);

    void deleteAddressById(long id);

    AddressDto getAddressById(long id);
}
