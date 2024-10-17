package hu.otp.assignment.controller;

import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.PlatformDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contactDetails")
@Validated
public interface ContactDetailController {



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createContactDetail(@RequestBody @Valid ContactDetailDto contactDetailDto);

    @GetMapping("/{personId}")
    List<ContactDetailDto> getContactDetailsByPersonId(@PathVariable long personId);

    @PutMapping("addContactDetail")
    void addContactDetailToPerson(@RequestBody @Valid ContactDetailDto contactDetailDto);

    @PutMapping("/editContactDetail/{id}")
    void editContactDetail(@RequestBody @Valid ContactDetailDto contactDetailDto, @PathVariable long id);

    @DeleteMapping("/deleteContactDetail/{personId}")
    void deleteContactDetail(@PathVariable long personId, @RequestBody @Valid PlatformDto platformDto);

}
