package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.AddressDto;
import hu.otp.assignment.dto.mapper.AddressMapper;
import hu.otp.assignment.exception.NoAddressWithSuchIdException;
import hu.otp.assignment.exception.PermAddressInUseException;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTests {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Test
    void givenNoAddress_WhenDeleteAddressById_ThenExceptionThrown() throws Exception {
        assertThrows(NoAddressWithSuchIdException.class,
                () -> addressServiceImpl.deleteAddressById(1));
    }

    @Test
    void givenPermanentAddress_WhenDeleteAddressById_ThenExceptionThrown() throws Exception {
        Address address = mock(Address.class);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(address.getPerson()).thenReturn(new Person());
        when(address.isPermanent()).thenReturn(true);
        assertThrows(PermAddressInUseException.class,
                () -> addressServiceImpl.deleteAddressById(1));
    }

    @Test
    void givenNoAddress_WhenGetAddressById_ThenExceptionThrown() throws Exception {
        assertThrows(NoAddressWithSuchIdException.class,
                () -> addressServiceImpl.getAddressById(1));
    }

    @Test
    void givenAddressInDb_WhenDeleteAddressById_ThenAddressDeleted() throws Exception {
        Address address = new Address();
        address.setId(1L);
        address.setPermanent(true);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        addressServiceImpl.deleteAddressById(1);
        verify(addressRepository).delete(address);
    }

    @Test
    void givenDto_WhenCreateAddress_ThenAddressCreated() throws Exception {
        AddressDto addressDto = new AddressDto(2000, "Szentendre",null, null, true);
        Address address = new Address();
        address.setZipCode(addressDto.zipCode());
        address.setCity(addressDto.city());
        address.setPermanent(addressDto.isPermanent());
        when(addressMapper.dtoToEntity(addressDto)).thenReturn(address);
        addressServiceImpl.createAddress(addressDto);
        verify(addressRepository).save(address);
    }
}
