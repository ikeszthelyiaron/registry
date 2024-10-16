package hu.otp.assignment.service.impl;

import hu.otp.assignment.dto.PersonDto;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

        private final PersonRepository personRepository;

    @Override
    public PersonDto getPersonById(long id) {
        return null;
    }

    @Override
    public void deletePerson(long id) {

    }

    @Override
    public void createPerson(PersonDto personDto) {

    }
}
