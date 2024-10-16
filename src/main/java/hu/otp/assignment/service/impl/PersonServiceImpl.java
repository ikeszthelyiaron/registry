package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.dto.mapper.PersonMapper;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

        private final PersonRepository personRepository;
        private final AddressRepository addressRepository;
        private final PersonMapper personMapper;

    @Override
    public PersonResponseDto getPersonById(long id) {
        PersonResponseDto result = null;
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + id));
        if(person != null) {
            result = personMapper.entityToResponseDto(person);
        }
        return result;
    }



    @Override
    public void deletePerson(long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + id));
        if(person != null) {
            Address permanent = person.getPermanent();
            permanent.removePerson(person);
            Address temporary = person.getTemporary();
            if(temporary != null) {
                temporary.removePerson(person);
            }
            personRepository.delete(person);    //elv trl a ContDe-kt is --> el őrz!
        }
    }

    @Override
    public void createPerson(RegisterPersonDto registerPersonDto) {
        Person person = new Person();
        person.setName(registerPersonDto.name());
        Address permanent = addressRepository.findById(
                registerPersonDto.permanentAddressId())
                .orElseThrow(() -> new RuntimeException("There is no Address with id " + registerPersonDto.permanentAddressId()));
        if(permanent != null) {
            person.setPermanent(permanent);
        }
        if(registerPersonDto.temporaryAddressId() != null) {
            Address temporary = addressRepository.findById(
                            registerPersonDto.temporaryAddressId())
                    .orElseThrow(() -> new RuntimeException("There is no Address with id " + registerPersonDto.temporaryAddressId()));
            if(temporary != null) {
                if(!permanent.equals(temporary)) {
                    person.setTemporary(temporary);
                } else {
                    throw new RuntimeException("Permanent and temporary Addresses are not supposed to be the same");
                }
            }
        }
        personRepository.save(person);
    }
}
