package south.park.parkshark.domain.dto.response;

import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.entities.MembershipLevels;

import java.util.List;

public class MemberDto {
    private final long memberId;
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String postalLabel;
    private final List<ContactDataDto> contactData;
    private final MembershipLevels membershipLevel;
    private final LicensePlateDto licensePlate;

    public MemberDto(long memberId, String firstName, String lastName, String streetName, String streetNumber, String postalCode,
                     String postalLabel, List<ContactDataDto> contactData, MembershipLevels membershipLevel,
                     LicensePlateDto licensePlate){

        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.postalLabel = postalLabel;
        this.contactData = contactData;
        this.membershipLevel = membershipLevel;
        this.licensePlate = licensePlate;
    }
}
