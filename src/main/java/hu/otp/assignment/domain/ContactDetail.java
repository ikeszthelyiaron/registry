package hu.otp.assignment.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table
@Entity
public class ContactDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    @JsonManagedReference
    private Person person;

    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Column(name = "identifier")
    private String identifier;


}
