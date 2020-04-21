package south.park.parkshark.mappers;

import org.springframework.stereotype.Component;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import south.park.parkshark.domain.dto.response.DivisionDto;
import south.park.parkshark.entities.Division;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {


    public Division toDivision(CreateDivisionDto createDivisionDto) {
        return new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), createDivisionDto.getDirectorName());
    }

    public DivisionDto toDivisionDto(Division division) {
        return new DivisionDto(division.getId(), division.getName(), division.getOriginalName(), division.getDirectorName());
    }

    public Collection<DivisionDto> toDivisionDto(Collection<Division> divisionCollection) {
      return divisionCollection.stream().map(this::toDivisionDto).collect(Collectors.toList());
    }
}
