package south.park.parkshark.datastore.entities;

import javax.persistence.*;

@Entity
@Table(name = "parking_categories")
public class ParkingCategory {
    @Id
    @Column(name = "parking_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parking_category_name")
    private String name;

    public ParkingCategory() {
   }

    public ParkingCategory(long id, String name) {
        this.id = id;
        this.name =name;
    }

    public ParkingCategory(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ParkingCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
