package hu.otp.assignment.controller;

import hu.otp.assignment.dto.PersonResponseDto;
import hu.otp.assignment.dto.RegisterPersonDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
public interface PersonController {

    /*
    UPDATE:
    1. név mód
    2. perm Addr mód
    3. temp Add hoz ad
    4. temp Add mód
    5. temp Add trl
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createPerson(@RequestBody @Valid RegisterPersonDto registerPersonDto);

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable long id);

    @GetMapping("/{id}")
    PersonResponseDto getPersonById(@PathVariable long id);

}
