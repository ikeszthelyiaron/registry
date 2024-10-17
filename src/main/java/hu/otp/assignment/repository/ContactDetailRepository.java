package hu.otp.assignment.repository;

import hu.otp.assignment.domain.ContactDetail;
import hu.otp.assignment.domain.Platform;
import hu.otp.assignment.dto.ContactDetailDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContactDetailRepository extends ListCrudRepository<ContactDetail, Long> {

    @Query("SELECT new hu.otp.assignment.dto.ContactDetailDto(cd.platform, cd.identifier, cd.person.id) from ContactDetail cd WHERE cd.person.id = :personId")
    List<ContactDetailDto> findAllByPersonId(long personId);

    @Query("DELETE FROM ContactDetail cd where cd.platform = :platform AND cd.person.id = :personId")
    void deleteByPersonIdAndPlatform(long personId, Platform platform);

    @Query("SELECT cd ContactDetail FROM ContactDetail cd WHERE cd.platform = :platform " +
            "AND cd.person.id = :personId")
    Optional<ContactDetail> findByPersonIdAndPlatform(long personId, Platform platform);


    boolean existsByPlatformAndIdentifier(Platform platform, String identifier);

}
