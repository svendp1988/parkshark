package south.park.parkshark.entities;

import javax.persistence.*;

@Entity
@Table(name ="divisions")
public class Division {
    @Id
    @Column(name = "division_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "director_full_name")
    private String directorName;

    public Division() {
    }


    public Division(String name, String originalName, String directorName) {
        this.name = name;
        this.originalName = originalName;
        this.directorName = directorName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirectorName() {
        return directorName;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
