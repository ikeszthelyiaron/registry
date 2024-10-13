package hu.otp.assignment.dto.mapper;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.PersonDto;
import hu.otp.assignment.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonMapper {

    private final AddressRepository addressRepository;

    public Person dtoToEntity(PersonDto dto) {
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

    public PersonDto entityToDto(Person entity) {
        return  PersonDto.builder()
                .name(entity.getName())
                .permanentAddressId(entity.getPermanent().getId())
                .temporaryAddressId(entity.getTemporary().getId())
                .build();
    }
}
