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
    private List<ContactData> contactData = new ArrayList<>();

    public Person() {
    }

    public Person(Long personId, Address address, String firstName, String lastName, List<ContactData> contactData){
        this.personId = personId;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactData = contactData;
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
        return contactData;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", addressId=" + address +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact list = " + contactData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return personId == person.personId;
    }

    @Override
    public int hashCode() {
        return (int) (personId ^ (personId >>> 32));
    }
}
