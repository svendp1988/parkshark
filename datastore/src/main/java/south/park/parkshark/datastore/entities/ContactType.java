package south.park.parkshark.datastore.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    @Column(name = "contact_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactTypeId;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ContactTypes contactType;

    public ContactType() {
    }

    public int getContactTypeId() {
        return contactTypeId;
    }

    public ContactTypes getContactType() {
        return contactType;
    }
}
