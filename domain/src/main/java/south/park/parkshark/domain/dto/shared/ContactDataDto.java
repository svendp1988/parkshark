package south.park.parkshark.domain.dto.shared;

import south.park.parkshark.entities.ContactType;
import south.park.parkshark.entities.ContactTypes;

import java.util.Objects;

public class ContactDataDto {
    private final ContactTypes type;
    private final String data;

    public ContactDataDto(ContactTypes type, String data){
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
