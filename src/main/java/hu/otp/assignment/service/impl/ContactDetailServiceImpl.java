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
        ContactDetail contactDetail = contactDetailRepository
                .findByPersonIdAndPlatform(personId, platform).get();
                if(contactDetail == null) {
                    throw new RuntimeException("There is no Contact Detail with id "
                            + contactDetail.getId() + " and Platform: " + platform );
                }
        person.removeContactDetail(platform);       //CASCADE miatt n fölösl?
        contactDetailRepository.delete(contactDetail);
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
        person.removeContactDetailById(id);
        person.addContactDetail(contactDetail);
        contactDetailRepository.save(contactDetail);
    }

    @Override
    public void addContactDetailToPerson(ContactDetailDto contactDetailDto) {
        ContactDetail contactDetail = contactDetailMapper.dtoToEntity(contactDetailDto);
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

    @Override
    public void createContactDetail(ContactDetailDto contactDetailDto) {
        Person person = personRepository.findById(contactDetailDto.personId())
                        .orElseThrow(() -> new RuntimeException("There is no Person with id " + contactDetailDto.personId()));
        ContactDetail contactDetail = contactDetailMapper.dtoToEntity(contactDetailDto);
        person.addContactDetail(contactDetail);
        contactDetailRepository.save(contactDetail);
    }
}
