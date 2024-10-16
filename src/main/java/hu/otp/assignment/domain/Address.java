package hu.otp.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hu.otp.assignment.util.BooleanStringConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

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
    @Convert(converter = BooleanStringConverter.class)
    private boolean isPermanent;

    public void removePerson(Person person) {
        this.person = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city, street, houseNumber);
    }
}
