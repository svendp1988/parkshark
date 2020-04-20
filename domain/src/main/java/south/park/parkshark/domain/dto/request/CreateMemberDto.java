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
    private final MembershipLevels membershipLevel;
    private final LicensePlateDto licensePlate;

    public CreateMemberDto(String firstName, String lastName, String streetName, String streetNumber,
                           String postalCode, String postalLabel, List<ContactDataDto> contactData,
                           MembershipLevels membershipLevel, LicensePlateDto licensePlate) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostalLabel() {
        return postalLabel;
    }

    public List<ContactDataDto> getContactData() {
        return contactData;
    }

    public MembershipLevels getMembershipLevel() {
        return membershipLevel;
    }

    public LicensePlateDto getLicensePlate() {
        return licensePlate;
    }
}
