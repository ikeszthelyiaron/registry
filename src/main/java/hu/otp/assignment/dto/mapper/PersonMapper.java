package hu.otp.assignment.dto.mapper;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonMapper {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ContactDetailMapper contactDetailMapper;

    public Person dtoToEntity(RegisterPersonDto dto) {
        Person person = new Person();
        person.setName(dto.name());
        if(dto.temporaryAddressId() != null) {
            Address temporaryAddress = addressRepository.findById(dto.permanentAddressId())
                    .orElseThrow(() -> new RuntimeException("There is no address with id " + dto.permanentAddressId()));
            if(temporaryAddress != null) {
                person.setTemporary(temporaryAddress);
            }
        }
        Address permanentAddress = addressRepository.findById(dto.permanentAddressId())
                .orElseThrow(() -> new RuntimeException("There is no address with id " + dto.permanentAddressId()));
        if(permanentAddress != null) {
            person.setPermanent(permanentAddress);
        }
        return person;
    }

    public RegisterPersonDto entityToDto(Person entity) {
        return  RegisterPersonDto.builder()
                .name(entity.getName())
                .permanentAddressId(entity.getPermanent().getId())
                .temporaryAddressId(entity.getTemporary().getId())
                .build();
    }

    public PersonResponseDto entityToResponseDto(Person entity) {
        return PersonResponseDto.builder()
                .name(entity.getName())
                .permanent(addressMapper.entityToDto(entity.getPermanent()))
                .temporary(addressMapper.entityToDto(entity.getTemporary()))
                .contactDetails(entity.getContactDetails()
                        .stream()
                        .map(contactDetailMapper::entityToResponseDto)
                        .toList())
                .build();
    }
}
