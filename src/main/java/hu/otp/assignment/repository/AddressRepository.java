package hu.otp.assignment.repository;

import hu.otp.assignment.domain.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address, Long> {
}
