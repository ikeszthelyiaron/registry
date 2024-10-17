package hu.otp.assignment;

import hu.otp.assignment.domain.ContactDetail;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.ContactDetailDto;
import hu.otp.assignment.dto.PlatformDto;
import hu.otp.assignment.dto.mapper.ContactDetailMapper;
import hu.otp.assignment.exception.*;
import hu.otp.assignment.repository.ContactDetailRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.impl.ContactDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static hu.otp.assignment.domain.Platform.SKYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static hu.otp.assignment.domain.Platform.TWITTER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTests {


    @Mock
    ContactDetailRepository contactDetailRepository;

    @Mock
    ContactDetailMapper contactDetailMapper;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    private ContactDetailServiceImpl contactDetailServiceImpl;

    @Test
    void givenNoPersonInDB_WhenDeleteContactDetail_ThenExceptionThrown() throws Exception{
        PlatformDto platformDto = new PlatformDto(TWITTER);
        assertThrows(NoPersonWithSuchIdException.class,
                () -> contactDetailServiceImpl.deleteContactDetail(1, platformDto));
    }

    @Test
    void givenNoContactDetails_WhenDeleteContactDetail_ThenExceptionThrown() throws Exception{
        PlatformDto platformDto = new PlatformDto(TWITTER);
        when(personRepository.findById(1L)).thenReturn(Optional.of(new Person()));
        assertThrows(NoSuchContactDetailException.class,
                () -> contactDetailServiceImpl.deleteContactDetail(1, platformDto));
    }

    @Test
    void givenNoContactDetails_WhenEditContactDetail_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        assertThrows(NoContactDetailWithSuchIdException.class,
                () -> contactDetailServiceImpl.editContactDetail(contactDetailDto, 1));
    }

    @Test
    void givenNoPerson_WhenEditContactDetail_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        when(contactDetailRepository.findById(1L)).thenReturn(Optional.of(new ContactDetail()));
        assertThrows(NoPersonWithSuchIdException.class,
                () -> contactDetailServiceImpl.editContactDetail(contactDetailDto, 1));
    }

    @Test
    void givenPresenceOnPlatform_WhenEditContactDetail_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        Person person = mock(Person.class);
        ContactDetail contactDetail = mock(ContactDetail.class);
        person.setContactDetails(new ArrayList<ContactDetail>());
        when(contactDetailRepository.findById(1L)).thenReturn(Optional.of(contactDetail));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(person.hasSuchPlatform(contactDetailDto.platform())).thenReturn(true);
        when(person.findByPlatform(contactDetailDto.platform())).thenReturn(contactDetail);
        when(person.findByPlatform(contactDetailDto.platform()).getId()).thenReturn(5L);
        assertThrows(AlreadyPresentOnPlatformException.class,
                () -> contactDetailServiceImpl.editContactDetail(contactDetailDto, 1));
    }

    @Test
    void givenExistingContactDetail_WhenEditContactDetail_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        Person person = mock(Person.class);
        ContactDetail contactDetail = mock(ContactDetail.class);
        person.setContactDetails(new ArrayList<ContactDetail>());
        when(contactDetailRepository.findById(1L)).thenReturn(Optional.of(contactDetail));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(person.hasSuchPlatform(contactDetailDto.platform())).thenReturn(true);
        when(person.findByPlatform(contactDetailDto.platform())).thenReturn(contactDetail);
        when(person.findByPlatform(contactDetailDto.platform()).getId()).thenReturn(1L);
        when(contactDetailServiceImpl.contactDetailExists(contactDetail)).thenReturn(true);
        assertThrows(ContactDetailAlreadyExistsException.class,
                () -> contactDetailServiceImpl.editContactDetail(contactDetailDto, 1));
    }

    @Test
    void givenExistingContactDetail_WhenAddContactDetailToPerson_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        ContactDetail contactDetail = mock(ContactDetail.class);
        when(contactDetailMapper.dtoToEntity(contactDetailDto)).thenReturn(contactDetail);
        when(contactDetailServiceImpl.contactDetailExists(contactDetail)).thenReturn(true);
        assertThrows(ContactDetailAlreadyExistsException.class,
                () -> contactDetailServiceImpl.addContactDetailToPerson(contactDetailDto));
    }

    @Test
    void givenNoPerson_WhenAddContactDetailToPerson_ThenExceptionThrown() throws Exception{
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        ContactDetail contactDetail = mock(ContactDetail.class);
        when(contactDetailMapper.dtoToEntity(contactDetailDto)).thenReturn(contactDetail);
        when(contactDetailServiceImpl.contactDetailExists(contactDetail)).thenReturn(false);
        assertThrows(NoPersonWithSuchIdException.class,
                () -> contactDetailServiceImpl.addContactDetailToPerson(contactDetailDto));
    }

    @Test
    void givenPresenceOnPlatform_WhenAddContactDetailToPerson_ThenExceptionThrown() throws Exception{
        Person person = mock(Person.class);
        ContactDetailDto contactDetailDto = new ContactDetailDto(SKYPE, "ABC", 1);
        ContactDetail contactDetail = mock(ContactDetail.class);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(person.hasSuchPlatform(contactDetailDto.platform())).thenReturn(true);
        when(contactDetailMapper.dtoToEntity(contactDetailDto)).thenReturn(contactDetail);
        when(contactDetailServiceImpl.contactDetailExists(contactDetail)).thenReturn(false);

        assertThrows(AlreadyPresentOnPlatformException.class,
                () -> contactDetailServiceImpl.addContactDetailToPerson(contactDetailDto));
    }

    @Test
    void givenNoPerson_WhenGetContactDetailsByPersonId_ThenExceptionThrown() throws Exception {
        assertThrows(NoPersonWithSuchIdException.class,
                () -> contactDetailServiceImpl.getContactDetailsByPersonId(1));
    }














    }
