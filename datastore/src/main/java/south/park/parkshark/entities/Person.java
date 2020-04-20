package south.park.parkshark.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<ContactData> contactDataList = new ArrayList<>();

    public Person() {
    }

    public long getPersonId() {
        return personId;
    }

    public Address getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ContactData> getContactDataList() {
        return contactDataList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", addressId=" + address +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact list = " + contactDataList +
                '}';
    }
}
