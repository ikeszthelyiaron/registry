package hu.otp.assignment.service;

import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.PlatformDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ContactDetailService {

    void createContactDetail(ContactDetailDto contactDetailDto);

    List<ContactDetailDto> getContactDetailsByPersonId(@PathVariable long personId);

    void addContactDetailToPerson(@RequestBody ContactDetailDto contactDetailDto);

    void editContactDetail(ContactDetailDto contactDetailDto, long id);

    void deleteContactDetail(@PathVariable long personId, @RequestBody PlatformDto platformDto);
}
