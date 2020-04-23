package south.park.parkshark.datastore.entities;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "divisions")
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
    @Nullable
    @Column(name = "parent_division_id")
    private Long parentId;

    public Division() {
    }

    public Division(String name, String originalName, String directorName, Long parentId) {
        this.name = name;
        this.originalName = originalName;
        this.directorName = directorName;
        this.parentId = parentId;
    }

    public Division(long id, String name, String originalName, String directorName, long parentId) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.directorName = directorName;
        this.parentId = parentId;
    }

    public Division(String name, String originalName, String directorName) {
        this(name, originalName, directorName, null);
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

    public Long getParentId() {
        return parentId;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
