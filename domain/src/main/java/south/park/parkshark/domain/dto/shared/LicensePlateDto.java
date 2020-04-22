package south.park.parkshark.domain.dto.shared;

import java.util.Objects;

public class LicensePlateDto {
    private String plateNumber;
    private String issuingCountry;

    public LicensePlateDto() {
    }

    public LicensePlateDto(String plateNumber, String issuingCountry) {

        this.plateNumber = plateNumber;
        this.issuingCountry = issuingCountry;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlateDto that = (LicensePlateDto) o;
        return Objects.equals(plateNumber, that.plateNumber) &&
                Objects.equals(issuingCountry, that.issuingCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber, issuingCountry);
    }

    @Override
    public String toString() {
        return "LicensePlateDto{" +
                "plateNumber='" + plateNumber + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                '}';
    }
}
