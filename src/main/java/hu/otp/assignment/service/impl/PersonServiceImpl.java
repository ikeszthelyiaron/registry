package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.dto.mapper.PersonMapper;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.PersonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
            personRepository.delete(person);
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

    @Override
    public void deleteTemporary(long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + personId));
        if(person != null) {
            if(person.getTemporary() == null) {
                throw new RuntimeException("This person has no temporaryAddress to delete");
            }
            Address temporary = person.getTemporary();
            temporary.setPerson(null);
            person.setTemporary(null);
            personRepository.save(person);
            addressRepository.save(temporary);
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
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + personId));
        if(person != null) {
            if(person.getTemporary() != null) {
                throw new RuntimeException("This person already has a temporary Address");
            }
            Address newTemporary = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("There is no Address with id " + addressId));
            if(newTemporary != null) {
                if(newTemporary.isPermanent()) {
                    throw new RuntimeException("This Address is not temporary but permanent");
                }
                if(newTemporary.getPerson() != null) {
                    throw new RuntimeException("Someone is already associated with this address");
                }
                person.setTemporary(newTemporary);
                newTemporary.setPerson(person);
                personRepository.save(person);
                Address addressAgainFromDB = addressRepository.findById(addressId).get();
                System.out.println("abc");
//                addressRepository.save(newTemporary);
            } else {
                throw new RuntimeException("There is no Address with id " + addressId);
            }
        }
    }

    @Override
    public void changePermanentAddress(long addressId, long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + personId));
        if(person != null) {
            Address newPermanent = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("There is no Address with id " + addressId));
            if(newPermanent != null) {
                if(!newPermanent.isPermanent()) {
                    throw new RuntimeException("This address is not permanent");
                }
                if(newPermanent.getPerson() == null) {
                    Address oldPermanent = person.getPermanent();
                    oldPermanent.setPerson(null);
                    addressRepository.save(oldPermanent);
                    person.setPermanent(newPermanent);
                    newPermanent.setPerson(person);
                    personRepository.save(person);
                } else {
                    throw new RuntimeException("Someone is already associated with this address");
                }

            }
        }
    }

    @Override
    public void changeName(String name, long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Person with id " + id));
        if(person != null) {
            person.setName(name);
            personRepository.save(person);
        }
    }
}
