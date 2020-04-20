package south.park.parkshark.domain.dto.shared;

import south.park.parkshark.entities.ContactType;
import south.park.parkshark.entities.ContactTypes;

public class ContactDataDto {
    private final ContactTypes type;
    private final String data;

    public ContactDataDto(ContactTypes type, String data){
        this.type = type;
        this.data = data;
    }
}
