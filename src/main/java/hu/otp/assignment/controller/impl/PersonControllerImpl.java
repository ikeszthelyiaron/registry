package hu.otp.assignment.controller.impl;

import hu.otp.assignment.controller.PersonController;
import hu.otp.assignment.dto.PersonNameDto;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonService personService;

    @Override
    public PersonResponseDto getPersonById(long id) {
        return personService.getPersonById(id);
    }

    @Override
    public void deletePerson(long id) {
        personService.deletePerson(id);
    }

    @Override
    public void createPerson(RegisterPersonDto registerPersonDto) {
        personService.createPerson(registerPersonDto);
    }

    @Override
    public void deleteTemporary(long personId) {
        personService.deleteTemporary(personId);
    }

    @Override
    public void changeTemporaryAddress(long addressId, long personId) {
        personService.changeTemporaryAddress(addressId, personId);
    }

    @Override
    public void addTemporaryAddress(long addressId, long personId) {
        personService.addTemporaryAddress(addressId, personId);
    }

    @Override
    public void changePermanentAddress(long addressId, long personId) {
        personService.changePermanentAddress(addressId, personId);
    }

    @Override
    public void changeName(PersonNameDto personNameDto, long id) {
        personService.changeName(personNameDto, id);
    }
}
