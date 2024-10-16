package hu.otp.assignment.dto.mapper;

import hu.otp.assignment.domain.ContactDetail;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.ContactDetailResponseDto;
import hu.otp.assignment.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactDetailMapper {

    private final PersonRepository personRepository;

    public ContactDetail dtoToEntity(ContactDetailDto dto) {
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setPlatform(dto.platform());
        contactDetail.setIdentifier(dto.identifier());
        Person person = personRepository.findById(dto.personId())
                .orElseThrow(() -> new RuntimeException("There is no person with id " + dto.personId()));
        if(person != null) {
            contactDetail.setPerson(person);
        }
        return contactDetail;
    }

    public ContactDetailDto entityToDto(ContactDetail entity) {
        return new ContactDetailDto(entity.getPlatform(),
                entity.getIdentifier(), entity.getPerson().getId());
    }

    public ContactDetailResponseDto entityToResponseDto(ContactDetail entity) {
        return new ContactDetailResponseDto(entity.getPlatform(), entity.getIdentifier());
    }


}
