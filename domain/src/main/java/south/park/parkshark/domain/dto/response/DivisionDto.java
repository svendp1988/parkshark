package south.park.parkshark.domain.dto.response;

import java.util.Objects;

public class DivisionDto {

    private long id;

    private String name;

    private String originalName;

    private String directorName;

    public DivisionDto() {
    }

    public DivisionDto(long id, String name, String originalName, String directorName) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisionDto that = (DivisionDto) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(originalName, that.originalName) &&
                Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, originalName, directorName);
    }

    @Override
    public String toString() {
        return "DivisionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
