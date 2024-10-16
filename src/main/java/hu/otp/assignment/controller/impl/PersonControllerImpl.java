package hu.otp.assignment.controller.impl;

import hu.otp.assignment.controller.PersonController;
import hu.otp.assignment.dto.PersonDto;
import hu.otp.assignment.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonService personService;

    @Override
    public PersonDto getPersonById(long id) {
        return personService.getPersonById(id);
    }

    @Override
    public void deletePerson(long id) {
        personService.deletePerson(id);
    }

    @Override
    public void createPerson(PersonDto personDto) {
        personService.createPerson(personDto);
    }
}
