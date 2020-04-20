package south.park.parkshark.dtos;

public class CreateDivisionDto {

    private String name;

    private String originalName;

    private String directorName;

    public CreateDivisionDto() {
    }

    public CreateDivisionDto(String name, String originalName, String directorName) {
        this.name = name;
        this.originalName = originalName;
        this.directorName = directorName;
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
}
