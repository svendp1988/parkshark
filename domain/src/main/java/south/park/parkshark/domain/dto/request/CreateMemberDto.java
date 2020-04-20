package south.park.parkshark.domain.dto.request;

public class CreateMemberDto {
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String postalLabel;

    public CreateMemberDto(String firstName, String lastName, String streetName, String streetNumber, String postalCode, String postalLabel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.postalLabel = postalLabel;
    }
}
