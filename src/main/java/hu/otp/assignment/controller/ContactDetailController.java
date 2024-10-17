package hu.otp.assignment.controller;

import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contactDetails")
public interface ContactDetailController {


    //TODO: dto-k validálása midnenhol

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createContactDetail(ContactDetailDto contactDetailDto);

    @GetMapping("/{personId}")
    List<ContactDetailDto> getContactDetailsByPersonId(@PathVariable long personId);

    @PutMapping("addContactDetail")
    void addContactDetailToPerson(@RequestBody ContactDetailDto contactDetailDto);

    @PutMapping("/editContactDetail")
    void editContactDetail(ContactDetailDto contactDetailDto);

    @DeleteMapping("/deleteContactDetail/{personId}")
    void deleteContactDetail(@PathVariable long personId, @RequestBody Platform platform);

}
