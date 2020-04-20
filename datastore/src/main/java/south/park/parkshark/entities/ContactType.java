package south.park.parkshark.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactTypeId;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ContactTypes contact_type;

    public ContactType() {
    }

    public int getContactTypeId() {
        return contactTypeId;
    }

    public ContactTypes getContact_type() {
        return contact_type;
    }
}
