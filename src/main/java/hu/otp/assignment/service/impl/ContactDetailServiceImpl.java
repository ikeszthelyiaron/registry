package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.ContactDetail;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.PlatformDto;
import hu.otp.assignment.dto.mapper.ContactDetailMapper;
import hu.otp.assignment.repository.ContactDetailRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.ContactDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactDetailServiceImpl implements ContactDetailService {

    private final ContactDetailRepository contactDetailRepository;
    private final ContactDetailMapper contactDetailMapper;
    private final PersonRepository personRepository;

    @Override
    public void deleteContactDetail(long personId, PlatformDto platformDto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + personId));
        Platform platform = platformDto.platform();
        Optional<ContactDetail> contactDetail = contactDetailRepository
                .findByPersonIdAndPlatform(personId, platform);
        if(contactDetail.isEmpty()) {
            throw new RuntimeException("There is no Contact Detail with personId "
                    + personId + " and Platform: " + platform );
        } else {
            person.removeContactDetail(platform);       //CASCADE miatt n fölösl?
            contactDetailRepository.delete(contactDetail.get());
        }
    }

    @Override
    public void editContactDetail(ContactDetailDto contactDetailDto, long id) {
        ContactDetail contactDetail = contactDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no ContactDetail with id " + id));
        Person person = personRepository.findById(contactDetailDto.personId())
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + contactDetailDto.personId()));
        contactDetail.setPlatform(contactDetailDto.platform());
        contactDetail.setIdentifier(contactDetailDto.identifier());
        contactDetail.setPerson(person);
        if(contactDetailExists(contactDetail)) {
            throw new RuntimeException("There is already a Contact Detail with this Platform and identifier: "
                    + contactDetail.getPlatform() + " " + contactDetail.getIdentifier());
        }
        person.removeContactDetailById(id);
        person.addContactDetail(contactDetail);
        contactDetailRepository.save(contactDetail);
    }

    private boolean contactDetailExists(ContactDetail contactDetail) {
        return contactDetailRepository.
                existsByPlatformAndIdentifier(contactDetail.getPlatform(), contactDetail.getIdentifier());
    }

    @Override
    public void addContactDetailToPerson(ContactDetailDto contactDetailDto) {
        ContactDetail contactDetail = contactDetailMapper.dtoToEntity(contactDetailDto);
        if(contactDetailExists(contactDetail)) {
            throw new RuntimeException("There is already a Contact Detail with this Platform and identifier: "
                    + contactDetail.getPlatform() + " " + contactDetail.getIdentifier());
        }
        Person person = personRepository.findById(contactDetailDto.personId())
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + contactDetailDto.personId()));
        person.addContactDetail(contactDetail);
        contactDetailRepository.save(contactDetail);
    }

    @Override
    public List<ContactDetailDto> getContactDetailsByPersonId(long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + personId));
        return contactDetailRepository.findAllByPersonId(personId);
    }

}
