package south.park.parkshark.domain.dto.request;

import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.entities.MembershipLevels;

import java.util.List;

public class CreateMemberDto {
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String postalLabel;
    private final List<ContactDataDto> contactData;
    private final MembershipLevels level;
    private final LicensePlateDto licensePlate;

    public CreateMemberDto(String firstName, String lastName, String streetName, String streetNumber,
                           String postalCode, String postalLabel, List<ContactDataDto> contactData,
                           MembershipLevels level, LicensePlateDto licensePlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.postalLabel = postalLabel;
        this.contactData = contactData;
        this.level = level;
        this.licensePlate = licensePlate;
    }
}
