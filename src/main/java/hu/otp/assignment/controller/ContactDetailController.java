package hu.otp.assignment.controller;

import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.PlatformDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contactDetails")
@Validated
public interface ContactDetailController {


    @GetMapping("/{personId}")
    List<ContactDetailDto> getContactDetailsByPersonId(@PathVariable long personId);

    @PutMapping("/add-contactdetail")
    void addContactDetailToPerson(@RequestBody @Valid ContactDetailDto contactDetailDto);

    @PutMapping("/edit-contactdetail/{id}")
    void editContactDetail(@RequestBody @Valid ContactDetailDto contactDetailDto, @PathVariable long id);

    @DeleteMapping("/delete-contactdetail/{personId}")
    void deleteContactDetail(@PathVariable long personId, @RequestBody @Valid PlatformDto platformDto);

}
