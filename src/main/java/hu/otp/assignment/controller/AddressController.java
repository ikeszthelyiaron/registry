package hu.otp.assignment.controller;

import hu.otp.assignment.dto.AddressDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/address")
public interface AddressController {
/*
    1. új cím bevitele: DTO, VOID!
    2. cím törl (csak ha nincs hozzá Person!) VOID!
 */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createAddress(@RequestBody @Valid AddressDto addressDto);

    @DeleteMapping("/{id}")
    void deleteAddressById(@PathVariable("id") long id);

}
