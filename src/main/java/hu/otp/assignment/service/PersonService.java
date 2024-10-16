package hu.otp.assignment.service;

import hu.otp.assignment.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonService {

    void createPerson(@RequestBody @Valid PersonDto personDto);

    void deletePerson(@PathVariable long id);

    PersonDto getPersonById(@PathVariable long id);
}
