package hu.otp.assignment.service;

import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonService {

    void createPerson(@RequestBody @Valid RegisterPersonDto registerPersonDto);

    void deletePerson(@PathVariable long id);

    PersonResponseDto getPersonById(@PathVariable long id);
}
