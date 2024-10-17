package hu.otp.assignment.controller;

import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@Validated
public interface PersonController {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createPerson(@RequestBody @Valid RegisterPersonDto registerPersonDto);

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable long id);

    @GetMapping("/{id}")
    PersonResponseDto getPersonById(@PathVariable long id);

    @PutMapping("/{id}")
    void changeName(@RequestBody String name, @PathVariable long id);

    @PutMapping("/changePermanent/{personId}/{addressId}")
    void changePermanentAddress(@PathVariable long personId, @PathVariable long addressId);

    @PutMapping("/addTemporary/{personId}/{addressId}")
    void addTemporaryAddress(@PathVariable long personId, @PathVariable long addressId);

    @PutMapping("/changeTemporary/{personId}/{addressId}")
    void changeTemporaryAddress(@PathVariable long personId, @PathVariable long addressId);

    @DeleteMapping("/deleteTemporary/{personId}")
    void deleteTemporary(@PathVariable long personId);

}
