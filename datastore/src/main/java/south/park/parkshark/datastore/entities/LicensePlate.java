package south.park.parkshark.datastore.entities;

import javax.persistence.*;

@Entity
@Table(name = "license_plates")
public class LicensePlate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_plate_id")
    private long licensePlateId;

    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "country")
    private String country;

    public LicensePlate(){}

    public LicensePlate(String licenseNumber, String country){
        this.licenseNumber = licenseNumber;
        this.country = country;
    }
    public LicensePlate(long licensePlateId, String licenseNumber, String country){
        this.licensePlateId = licensePlateId;
        this.licenseNumber = licenseNumber;
        this.country = country;
    }

    public long getLicensePlateId() {
        return licensePlateId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getCountry() {
        return country;
    }
}
