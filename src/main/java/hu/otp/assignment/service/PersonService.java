package hu.otp.assignment.service;

import hu.otp.assignment.dto.PersonNameDto;
import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonService {

    void createPerson(@RequestBody @Valid RegisterPersonDto registerPersonDto);

    void deletePerson(@PathVariable long id);

    PersonResponseDto getPersonById(long id);

    void changeName(PersonNameDto personNameDto, long id);

    void changePermanentAddress( long addressId, long personId);

    void addTemporaryAddress( long addressId, long personId);

    void changeTemporaryAddress(long addressId, long personId);

    void deleteTemporary(long personId);
}
