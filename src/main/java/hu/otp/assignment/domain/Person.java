package hu.otp.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@Table
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "temporary_address")
    @JsonManagedReference
    private Address temporary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address")
    @JsonManagedReference
    private Address permanent;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @ToString.Exclude
    private List<ContactDetail> contactDetails;

    public void addContactDetail(ContactDetail contactDetail) {
        contactDetails.add(contactDetail);
    }

    public void removeContactDetail(Platform platform) {
        contactDetails.removeIf(contactDetail -> contactDetail.getPlatform().equals(platform));
    }

    public void removeContactDetailById(long contactDetailId) {
        contactDetails.removeIf(contactDetail -> contactDetail.getId().equals(contactDetailId));
    }

    public boolean hasSuchPlatform(Platform platform) {
        return contactDetails.stream().anyMatch(contactDetail -> contactDetail.getPlatform().equals(platform));
    }

    public ContactDetail findByPlatform(Platform platform) {
        return contactDetails.stream().filter(contactDetail -> contactDetail.getPlatform().equals(platform)).findFirst().orElse(null);
    }

}
