package south.park.parkshark.domain.dto.response;

import south.park.parkshark.entities.Address;
import south.park.parkshark.entities.Division;
import south.park.parkshark.entities.ParkingCategory;
import south.park.parkshark.entities.Person;

import java.util.Objects;

public class ParkingLotDto {

    private long id;

    private String name;

    private int maxCapacity;

    private long pricePerHour;

    private Person contactPerson;

    private Address address;

    private Division division;

    private ParkingCategory parkingCategory;


    public ParkingLotDto(long id, String name, int maxCapacity, long pricePerHour, Person contactPerson, Address address, Division division, ParkingCategory parkingCategory) {
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.pricePerHour = pricePerHour;
        this.contactPerson = contactPerson;
        this.address = address;
        this.division = division;
        this.parkingCategory = parkingCategory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getPricePerHour() {
        return pricePerHour;
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public Division getDivision() {
        return division;
    }

    public ParkingCategory getParkingCategory() {
        return parkingCategory;
    }

    @Override
    public String toString() {
        return "ParkingLotDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCapacity='" + maxCapacity + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", contactPerson=" + contactPerson +
                ", address=" + address +
                ", division=" + division +
                ", parkingCategory=" + parkingCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotDto that = (ParkingLotDto) o;
        return id == that.id &&
                maxCapacity == that.maxCapacity &&
                pricePerHour == that.pricePerHour &&
                Objects.equals(name, that.name) &&
                Objects.equals(contactPerson, that.contactPerson) &&
                Objects.equals(address, that.address) &&
                Objects.equals(division, that.division) &&
                Objects.equals(parkingCategory, that.parkingCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxCapacity, pricePerHour, contactPerson, address, division, parkingCategory);
    }
}
