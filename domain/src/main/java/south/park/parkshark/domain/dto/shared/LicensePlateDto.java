package south.park.parkshark.domain.dto.shared;

public class LicensePlateDto {
    private final String plateNumber;
    private final String issuingCountry;

    public LicensePlateDto(String plateNumber, String issuingCountry){

        this.plateNumber = plateNumber;
        this.issuingCountry = issuingCountry;
    }
}
