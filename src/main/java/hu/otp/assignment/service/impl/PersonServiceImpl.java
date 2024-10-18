package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.PersonNameDto;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.dto.mapper.PersonMapper;
import hu.otp.assignment.exception.*;
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
                .orElseThrow(() -> new NoPersonWithSuchIdException(id));
        if(person != null) {
            result = personMapper.entityToResponseDto(person);
        }
        return result;
    }



    @Override
    public void deletePerson(long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoPersonWithSuchIdException(id));
        if(person != null) {
            Address permanent = person.getPermanent();
            permanent.removePerson(person);
            Address temporary = person.getTemporary();
            if(temporary != null) {
                temporary.removePerson(person);
            }
            personRepository.delete(person);
        }
    }

    @Override
    public void createPerson(RegisterPersonDto registerPersonDto) {
        Person person = new Person();
        person.setName(registerPersonDto.name());
        Address permanent = addressRepository.findById(registerPersonDto.permanentAddressId())
                .orElseThrow(() ->
                        new NoAddressWithSuchIdException(registerPersonDto.permanentAddressId()));
        if(permanent != null) {
            person.setPermanent(permanent);
            permanent.setPerson(person);
        }
        if(registerPersonDto.temporaryAddressId() != null) {
            Address temporary = addressRepository.findById(
                            registerPersonDto.temporaryAddressId())
                    .orElseThrow(() ->
                            new NoAddressWithSuchIdException(registerPersonDto.temporaryAddressId()));
            if(temporary != null) {
                if(!permanent.equals(temporary)) {
                    person.setTemporary(temporary);
                } else {
                    throw new AddressClashException();
                }
            }
        }
        personRepository.save(person);
    }

    @Override
    public void deleteTemporary(long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
        if(person != null) {
            if(person.getTemporary() == null) {
                throw new NoTempAddressException(personId);
            }
            Address temporary = person.getTemporary();
            temporary.setPerson(null);
            person.setTemporary(null);
            personRepository.save(person);
        }
    }

    @Override
    public void changeTemporaryAddress(long addressId, long personId) {
        deleteTemporary(personId);
        addTemporaryAddress(addressId, personId);
    }

    @Override
    public void addTemporaryAddress(long addressId, long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
        if(person != null) {
            if(person.getTemporary() != null) {
                throw new AlreadyHasTempAddressException(personId);
            }
            Address newTemporary = addressRepository.findById(addressId)
                    .orElseThrow(() -> new NoAddressWithSuchIdException(addressId));
            if(newTemporary != null) {
                if(newTemporary.isPermanent()) {
                    throw new AddressIsPermanentException(newTemporary.getId());
                }
                if(newTemporary.getPerson() != null) {
                    throw new AddressAlreadyInUseException(addressId);
                }
                person.setTemporary(newTemporary);
                newTemporary.setPerson(person);
                personRepository.save(person);
            }
        }
    }

    @Override
    public void changePermanentAddress(long addressId, long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
        if(person != null) {
            Address newPermanent = addressRepository.findById(addressId)
                    .orElseThrow(() -> new NoAddressWithSuchIdException(addressId));
            if(newPermanent != null) {
                if(!newPermanent.isPermanent()) {
                    throw new AddressIsTemporaryException(newPermanent.getId());
                }
                if(newPermanent.getPerson() == null) {
                    Address oldPermanent = person.getPermanent();
                    oldPermanent.setPerson(null);
                    person.setPermanent(newPermanent);
                    newPermanent.setPerson(person);
                    personRepository.save(person);
                } else {
                    throw new AddressAlreadyInUseException(addressId);
                }

            }
        }
    }

    @Override
    public void changeName(PersonNameDto personNameDto, long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoPersonWithSuchIdException(id));
        if(person != null) {
            person.setName(personNameDto.name());
            personRepository.save(person);
        }
    }
}
