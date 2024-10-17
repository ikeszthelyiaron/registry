package hu.otp.assignment.service;

import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonService {

    void createPerson(@RequestBody @Valid RegisterPersonDto registerPersonDto);

    void deletePerson(@PathVariable long id);

    PersonResponseDto getPersonById(@PathVariable long id);

    void changeName(@RequestBody String name, @PathVariable long id);

    void changePermanentAddress(@RequestBody long addressId, @PathVariable long personId);

    void addTemporaryAddress(@RequestBody long addressId, @PathVariable long personId);

    void changeTemporaryAddress(@RequestBody long addressId, @PathVariable long personId);

    void deleteTemporary(@PathVariable long personId);
}
