package hu.otp.assignment.controller;

import hu.otp.assignment.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
public interface PersonController {

    /*
    !: cím: 1 v töb em is tart 1hez!

    UPDATE:
    1. név mód
    2. perm Addr mód
    3. temp Add hoz ad
    4. temp Add mód
    5. temp Add trl
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createPerson(@RequestBody @Valid PersonDto personDto);

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable long id);

    @GetMapping
    PersonDto getPersonById(@PathVariable long id);

}
