package hu.otp.assignment.repository;

import hu.otp.assignment.domain.ContactDetail;
import org.springframework.data.repository.ListCrudRepository;

public interface ContactDetailRepository extends ListCrudRepository<ContactDetail, Long> {
}
