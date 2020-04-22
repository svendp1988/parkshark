package south.park.parkshark.domain.dto.shared;

import south.park.parkshark.datastore.entities.ContactType;
import south.park.parkshark.datastore.entities.ContactTypes;

import java.util.Objects;

public class ContactDataDto {
    private ContactTypes type;
    private String data;

    public ContactDataDto() {
    }

    public ContactDataDto(ContactTypes type, String data) {
        this.type = type;
        this.data = data;
    }

    public ContactTypes getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDataDto that = (ContactDataDto) o;
        return type == that.type &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, data);
    }

    @Override
    public String toString() {
        return "ContactDataDto{" +
                "type=" + type +
                ", data='" + data + '\'' +
                '}';
    }
}
