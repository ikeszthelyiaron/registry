package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.RegisterPersonDto;
import hu.otp.assignment.dto.mapper.PersonMapper;
import hu.otp.assignment.exception.*;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTests {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PersonMapper personMapper;
    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @Test
    void givenNoPerson_WhenGetPersonById_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.getPersonById(1L));
    }

    @Test
    void givenNoPerson_WhenDeletePersonById_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.deletePerson(1L));
    }

    @Test
    void givenNoAddress_WhenCreatePerson_ThenExceptionThrown() throws Exception {
        RegisterPersonDto registerPersonDto = new RegisterPersonDto("Bill", 2L, 3L);

        assertThrows(NoAddressWithSuchIdException.class,
                () -> personServiceImpl.createPerson(registerPersonDto));
    }

    @Test
    void givenNonExistentTemporaryAddress_WhenCreatePerson_ThenExceptionThrown() throws Exception {
        RegisterPersonDto registerPersonDto = new RegisterPersonDto("Bill", 2L, 3L);
        Address permanent = mock(Address.class);
        when(addressRepository.findById(2L)).thenReturn(Optional.of(permanent));
        assertThrows(NoAddressWithSuchIdException.class,
                () -> personServiceImpl.createPerson(registerPersonDto));
    }

    @Test
    void givenSameTemAndPermaAddress_WhenCreatePerson_ThenExceptionThrown() throws Exception {
        RegisterPersonDto registerPersonDto = new RegisterPersonDto("Bill", 2L, 3L);
        Address permanent = mock(Address.class);
        Address temporary = mock(Address.class);
        when(addressRepository.findById(2L)).thenReturn(Optional.of(permanent));
        when(addressRepository.findById(3L)).thenReturn(Optional.of(permanent));
        assertThrows(AddressClashException.class,
                () -> personServiceImpl.createPerson(registerPersonDto));
    }

    @Test
    void givenNoPerson_WhenDeleteTemporary_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.deleteTemporary(1));
    }

    @Test
    void givenNoTemporary_WhenDeleteTemporary_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        assertThrows(NoTempAddressException.class,
                () -> personServiceImpl.deleteTemporary(1));
    }

    @Test
    void givenNoPerson_WhenAddTemporaryAddress_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.addTemporaryAddress(1, 1));
    }

    @Test
    void givenNoTemporary_WhenAddTemporaryAddress_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(person.getTemporary()).thenReturn(new Address());
        assertThrows(AlreadyHasTempAddressException.class,
                () -> personServiceImpl.addTemporaryAddress(1, 1));
    }

    @Test
    void givenNoAddress_WhenAddTemporaryAddress_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        assertThrows(NoAddressWithSuchIdException.class,
                () -> personServiceImpl.addTemporaryAddress(1, 1));
    }

    @Test
    void givenPermanentAddress_WhenAddTemporaryAddress_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        Address address = mock(Address.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(address.isPermanent()).thenReturn(true);
        assertThrows(AddressIsPermanentException.class,
                () -> personServiceImpl.addTemporaryAddress(1, 1));
    }

    @Test
    void givenPersonAssociatedWithAddress_WhenAddTemporaryAddress_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        Address address = mock(Address.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(address.isPermanent()).thenReturn(false);
        when(address.getPerson()).thenReturn(new Person());
        assertThrows(AddressAlreadyInUseException.class,
                () -> personServiceImpl.addTemporaryAddress(1, 1));
    }

    @Test
    void givenNoPerson_WhenChangePermanentAdderss_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.changePermanentAddress(3, 2));
    }

    @Test
    void givenNoAddress_WhenChangePermanentAdderss_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        assertThrows(NoAddressWithSuchIdException.class,
                () -> personServiceImpl.changePermanentAddress(1, 1));
    }

    @Test
    void givenTemporaryAddress_WhenChangePermanentAdderss_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        Address address = mock(Address.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(address.isPermanent()).thenReturn(false);
        assertThrows(AddressIsTemporaryException.class,
                () -> personServiceImpl.changePermanentAddress(1, 1));
    }

    @Test
    void givenAddressAssociatedWithAnotherPerson_WhenChangePermanentAdderss_ThenExceptionThrown() throws Exception {
        Person person = mock(Person.class);
        Address address = mock(Address.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(address.isPermanent()).thenReturn(true);
        when(address.getPerson()).thenReturn(new Person());
        assertThrows(AddressAlreadyInUseException.class,
                () -> personServiceImpl.changePermanentAddress(1, 1));
    }



    @Test
    void givenNoPerson_WhenChangeName_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> personServiceImpl.changeName("Bill", 2));
    }

    @Test
    void givenPersonInDB_WhenDeletePerson_ThenDeleteMethodInvoked() throws Exception {
        Person person = new Person();
        Address address = new Address();
        person.setPermanent(address);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        personServiceImpl.deletePerson(1);
        verify(personRepository).delete(person);
    }

    @Test
    void givenPerson_WhenChangeName_ThenNameChanged() throws Exception {
        Person person = new Person();
        person.setName("wrong name");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personServiceImpl.changeName("correct name", 1);
        assertEquals("correct name", person.getName());
        verify(personRepository).save(person);
    }

    @Test
    void givenPerson_WhenChangePermanentAddress_ThenPermanentAddressChanged() throws Exception {
        Person person = new Person();
        Address firstAddress = new Address();
        firstAddress.setCity("Debrecen");
        firstAddress.setPerson(person);
        firstAddress.setPermanent(true);
        Address secondAddress = new Address();
        secondAddress.setCity("Budapest");
        secondAddress.setPermanent(true);
        person.setPermanent(firstAddress);
        when(addressRepository.findById(2L)).thenReturn(Optional.of(secondAddress));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personServiceImpl.changePermanentAddress(2, 1);
        assertEquals(secondAddress, person.getPermanent());
        assertEquals(person, secondAddress.getPerson());
        verify(personRepository).save(person);
    }

    @Test
    void givenRegisterPersonDto_WhenCreatePerson_ThenPersonCreated() throws Exception {
        RegisterPersonDto registerPersonDto = new RegisterPersonDto("Legit name", 1L, 2L);
        Address permanent = new Address();
        permanent.setCity("Budapest");
        Address temporary = new Address();
        temporary.setCity("Debrecen");
        when(addressRepository.findById(1L)).thenReturn(Optional.of(permanent));
        when(addressRepository.findById(2L)).thenReturn(Optional.of(temporary));

        personServiceImpl.createPerson(registerPersonDto);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personCaptor.capture());
        Person person = personCaptor.getValue();
        assertEquals(permanent, person.getPermanent());
        assertEquals(temporary, person.getTemporary());
        assertEquals("Legit name", person.getName());
    }

    @Test
    void givenPersonWithoutTemporary_WhenAddTemporaryAddress_ThenAddressAdded() throws Exception {
        Person person = new Person();
        Address address = new Address();
        address.setPermanent(false);
        address.setCity("Budapest");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        personServiceImpl.addTemporaryAddress(1, 1);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personCaptor.capture());
        Person person1 = personCaptor.getValue();
        assertEquals(address, person1.getTemporary());
        assertEquals(person1, address.getPerson());
    }

    @Test
    void givenPersonWithTemporary_WhenDeleteTemporaryAddress_ThenAddressRemoved() throws Exception {
        Person person = new Person();
        Address address = new Address();
        address.setPermanent(false);
        address.setCity("Budapest");
        person.setTemporary(address);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personServiceImpl.deleteTemporary(1);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personCaptor.capture());
        Person person1 = personCaptor.getValue();
        assertNull(person1.getTemporary());
    }




    }
