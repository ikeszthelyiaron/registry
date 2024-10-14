package hu.otp.assignment.controller.impl;

import hu.otp.assignment.controller.AddressController;
import hu.otp.assignment.dto.AddressDto;
import hu.otp.assignment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @Override
    public void deleteAddressById(long id) {
        addressService.deleteAddressById(id);
    }

    @Override
    public void createAddress(AddressDto addressDto) {
        addressService.createAddress(addressDto);
    }
}
