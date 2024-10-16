package hu.otp.assignment.service.impl;

import hu.otp.assignment.domain.Address;
import hu.otp.assignment.domain.Person;
import hu.otp.assignment.dto.AddressDto;
import hu.otp.assignment.dto.mapper.AddressMapper;
import hu.otp.assignment.exception.NoAddressWithSuchIdException;
import hu.otp.assignment.exception.PermAddressInUseException;
import hu.otp.assignment.repository.AddressRepository;
import hu.otp.assignment.repository.PersonRepository;
import hu.otp.assignment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final PersonRepository personRepository;

    @Override
    public void createAddress(AddressDto addressDto) {
        Address address = addressMapper.dtoToEntity(addressDto);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoAddressWithSuchIdException(id));
        if(address != null) {
            Person person = address.getPerson();
            if(person != null) {
                if(!address.isPermanent()) {
                    person.setTemporary(null);
                    personRepository.save(person);
                } else {
                    throw new PermAddressInUseException(address.getId());
                }
            } else {
                addressRepository.delete(address);
            }
        }
    }

    @Override
    public AddressDto getAddressById(long id) {
        return addressMapper.entityToDto(addressRepository.findById(id)
                .orElseThrow(() -> new NoAddressWithSuchIdException(id)));
    }
}
