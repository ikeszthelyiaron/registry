package hu.otp.assignment.controller;

import hu.otp.assignment.dto.AddressDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/address")
@Validated
public interface AddressController {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createAddress(@RequestBody @Valid AddressDto addressDto);

    @DeleteMapping("/{id}")
    void deleteAddressById(@PathVariable("id") long id);

    @GetMapping("/{id}")
    AddressDto getAddressById(@PathVariable("id") long id);

}
