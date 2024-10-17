package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.service.ContactDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactDetailServiceImpl implements ContactDetailService {

    @Override
    public void deleteContactDetail(long personId, Platform platform) {

    }

    @Override
    public void editContactDetail(ContactDetailDto contactDetailDto) {

    }

    @Override
    public void addContactDetailToPerson(ContactDetailDto contactDetailDto) {

    }

    @Override
    public List<ContactDetailDto> getContactDetailsByPersonId(long personId) {
        return List.of();
    }

    @Override
    public void createContactDetail(ContactDetailDto contactDetailDto) {

    }
}
