package south.park.parkshark.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "person_id")
    private long personId;
    @Column(name = "address_id")
    private long addressId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Person() {
    }

    public long getPersonId() {
        return personId;
    }

    public long getAddressId() {
        return addressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", addressId=" + addressId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
