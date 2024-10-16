package hu.otp.assignment.controller;

import hu.otp.assignment.dto.PersonNameDto;
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
    void changeName(@RequestBody @Valid PersonNameDto personNameDto, @PathVariable long id);

    @PutMapping("/change-permanent/{personId}/{addressId}")
    void changePermanentAddress(@PathVariable long personId, @PathVariable long addressId);

    @PutMapping("/add-temporary/{personId}/{addressId}")
    void addTemporaryAddress(@PathVariable long personId, @PathVariable long addressId);

    @PutMapping("/change-temporary/{personId}/{addressId}")
    void changeTemporaryAddress(@PathVariable long personId, @PathVariable long addressId);

    @DeleteMapping("/delete-temporary/{personId}")
    void deleteTemporary(@PathVariable long personId);

}
