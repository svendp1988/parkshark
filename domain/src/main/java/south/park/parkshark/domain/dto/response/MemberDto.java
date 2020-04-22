package south.park.parkshark.domain.dto.response;

import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.datastore.entities.MembershipLevels;

import java.time.LocalDate;
import java.util.List;

public class MemberDto {
    private long memberId;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String postalLabel;
    private List<ContactDataDto> contactData;
    private MembershipLevels membershipLevel;
    private List<LicensePlateDto> licensePlate;
    private LocalDate registrationDate;

    public MemberDto() {
    }

    public MemberDto(long memberId, String firstName, String lastName, String streetName, String streetNumber, String postalCode,
                     String postalLabel, List<ContactDataDto> contactData, MembershipLevels membershipLevel,
                     List<LicensePlateDto> licensePlate, LocalDate registrationDate) {

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
        this.registrationDate = registrationDate;
    }

    public long getMemberId() {
        return memberId;
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

    public List<LicensePlateDto> getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberDto memberDto = (MemberDto) o;

        if (memberId != memberDto.memberId) return false;
        if (firstName != null ? !firstName.equals(memberDto.firstName) : memberDto.firstName != null) return false;
        if (lastName != null ? !lastName.equals(memberDto.lastName) : memberDto.lastName != null) return false;
        if (streetName != null ? !streetName.equals(memberDto.streetName) : memberDto.streetName != null) return false;
        if (streetNumber != null ? !streetNumber.equals(memberDto.streetNumber) : memberDto.streetNumber != null)
            return false;
        if (postalCode != null ? !postalCode.equals(memberDto.postalCode) : memberDto.postalCode != null) return false;
        if (postalLabel != null ? !postalLabel.equals(memberDto.postalLabel) : memberDto.postalLabel != null)
            return false;
        if (contactData != null ? !contactData.equals(memberDto.contactData) : memberDto.contactData != null)
            return false;
        if (membershipLevel != memberDto.membershipLevel) return false;
        if (licensePlate != null ? !licensePlate.equals(memberDto.licensePlate) : memberDto.licensePlate != null)
            return false;
        return registrationDate != null ? registrationDate.equals(memberDto.registrationDate) : memberDto.registrationDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (memberId ^ (memberId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (postalLabel != null ? postalLabel.hashCode() : 0);
        result = 31 * result + (contactData != null ? contactData.hashCode() : 0);
        result = 31 * result + (membershipLevel != null ? membershipLevel.hashCode() : 0);
        result = 31 * result + (licensePlate != null ? licensePlate.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", postalLabel='" + postalLabel + '\'' +
                ", contactData=" + contactData +
                ", membershipLevel=" + membershipLevel +
                ", licensePlate=" + licensePlate +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
