package south.park.parkshark.domain.dto.request;

import south.park.parkshark.datastore.entities.Address;
import south.park.parkshark.datastore.entities.Division;
import south.park.parkshark.datastore.entities.ParkingCategory;
import south.park.parkshark.datastore.entities.Person;


public class CreateParkingLotDto {

    private String name;

    private int maxCapacity;

    private long pricePerHour;

    private Person contactPerson;

    private Address address;

    private Division division;

    private ParkingCategory parkingCategory;

    public CreateParkingLotDto(String name, int maxCapacity, long pricePerHour, Person contactPerson, Address address, Division division, ParkingCategory parkingCategory) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.pricePerHour = pricePerHour;
        this.contactPerson = contactPerson;
        this.address = address;
        this.division = division;
        this.parkingCategory = parkingCategory;
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

    public void setContactPerson(Person contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setParkingCategory(ParkingCategory parkingCategory) {
        this.parkingCategory = parkingCategory;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "CreateParkingLotDto{" +
                "name='" + name + '\'' +
                ", maxCapacity='" + maxCapacity + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", contactPerson=" + contactPerson +
                ", address=" + address +
                ", division=" + division +
                ", parkingCategory=" + parkingCategory +
                '}';
    }
}
