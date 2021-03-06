package south.park.parkshark.datastore.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact_data")
public class ContactData {
    @Id
    @Column(name = "contact_data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactDataId;
    @Column(name = "person_id")
    private long personId;
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type")
    private ContactTypes contactType;
    @Column(name = "data")
    private String data;

    public ContactData() {
    }

    public ContactData(long personId, ContactTypes contactType, String data){
        this.personId = personId;
        this.contactType = contactType;
        this.data = data;
    }
    public ContactData(long contactDataId, long personId, ContactTypes contactType, String data){

        this.contactDataId = contactDataId;
        this.personId = personId;
        this.contactType = contactType;
        this.data = data;
    }
    public long getContactDataId() {
        return contactDataId;
    }

    public long getPersonId() {
        return personId;
    }

    public ContactTypes getContactType() {
        return contactType;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "contactData{" +
                "contactDataId=" + contactDataId +
                ", personId=" + personId +
                ", contactTypeId=" + contactType +
                ", data='" + data + '\'' +
                '}';
    }
}
