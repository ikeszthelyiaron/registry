package hu.otp.assignment.dto.mapper;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.AddressDto;
import hu.otp.assignment.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final PersonRepository personRepository;

    public Address dtoToEntity(AddressDto dto) {
        Address address = new Address();
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());
//        Person person = personRepository.findById(dto.personId())
//                .orElseThrow(() -> new RuntimeException("There is no person with id " + dto.personId()));
//        if(person != null) {
//            address.setPerson(person);
//        }
        if(dto.street() != null) {
            address.setStreet(dto.street());
        }
        if(dto.houseNumber() != null) {
            address.setHouseNumber(dto.houseNumber());
        }
        address.setPermanent(dto.isPermanent());
        return address;
    }

    public AddressDto entityToDto(Address entity) {
        return AddressDto.builder()
                .isPermanent(entity.isPermanent())
                .city(entity.getCity())
                .zipCode(entity.getZipCode())
                .street(entity.getStreet())
                .houseNumber(entity.getHouseNumber())
//                .personId(entity.getPerson().getId())
                .build();
    }

}
