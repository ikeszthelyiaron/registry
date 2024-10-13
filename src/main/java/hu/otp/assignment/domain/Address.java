package hu.otp.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Table
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber; //A 26/B típusú házszámok kezelése miatt String

    @OneToOne
    @ToString.Exclude
    @JsonBackReference
    private Person person;

    @Column(name = "address_type")
    @Convert(converter = Converter.class)
    private boolean isPermanent;

}
