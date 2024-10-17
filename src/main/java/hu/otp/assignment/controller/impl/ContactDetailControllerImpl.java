package hu.otp.assignment.controller.impl;

import hu.otp.assignment.controller.ContactDetailController;
import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.service.ContactDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactDetailControllerImpl implements ContactDetailController {

    private final ContactDetailService contactDetailService;

    @Override
    public void deleteContactDetail(long personId, Platform platform) {
        contactDetailService.deleteContactDetail(personId, platform);
    }

    @Override
    public void editContactDetail(ContactDetailDto contactDetailDto) {
        contactDetailService.editContactDetail(contactDetailDto);
    }

    @Override
    public void addContactDetailToPerson(ContactDetailDto contactDetailDto) {
        contactDetailService.addContactDetailToPerson(contactDetailDto);
    }

    @Override
    public List<ContactDetailDto> getContactDetailsByPersonId(long personId) {
        return contactDetailService.getContactDetailsByPersonId(personId);
    }

    @Override
    public void createContactDetail(ContactDetailDto contactDetailDto) {
        contactDetailService.createContactDetail(contactDetailDto);
    }
}
