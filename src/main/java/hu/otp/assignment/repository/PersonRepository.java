package hu.otp.assignment.repository;

import hu.otp.assignment.domain.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person, Long> {
}
