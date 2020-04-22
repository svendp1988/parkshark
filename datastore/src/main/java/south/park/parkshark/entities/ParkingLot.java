package south.park.parkshark.entities;

import javax.persistence.*;

@Entity
@Table(name = "parking_lots")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_lot_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "price_per_hour")
    private long pricePerHour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person contactPerson;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @ManyToOne
    @JoinColumn(name = "parking_category_id")
    private ParkingCategory parkingCategory;

    public ParkingLot() {
    }

    public ParkingLot(String name, int maxCapacity, long pricePerHour, Person contactPerson, Address address, Division division, ParkingCategory parkingCategory) {
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
        return "ParkingLot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCapacity='" + maxCapacity + '\'' +
                ", pricePerHour='" + pricePerHour + '\'' +
                ", contactPerson=" + contactPerson +
                ", address=" + address +
                ", division=" + division +
                ", parkingCategory=" + parkingCategory +
                '}';
    }
}
