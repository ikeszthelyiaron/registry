package hu.otp.assignment;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.mapper.AddressMapper;
import hu.otp.assignment.exception.NoAddressWithSuchIdException;
import hu.otp.assignment.exception.PermAddressInUseException;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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

}
