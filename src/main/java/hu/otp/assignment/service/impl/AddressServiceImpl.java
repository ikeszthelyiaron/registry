package hu.otp.assignment.service.impl;

import hu.otp.assignment.dto.AddressDto;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void createAddress(AddressDto addressDto) {

    }

    @Override
    public void deleteAddressById(long id) {

    }
}
