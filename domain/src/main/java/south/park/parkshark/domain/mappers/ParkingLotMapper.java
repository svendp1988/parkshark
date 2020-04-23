package south.park.parkshark.domain.mappers;

import org.springframework.stereotype.Component;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.datastore.entities.ParkingLot;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ParkingLotMapper {

    public ParkingLot toParkingLot(CreateParkingLotDto createParkingLotDto) {
        return new ParkingLot(createParkingLotDto.getName(), createParkingLotDto.getMaxCapacity(), createParkingLotDto.getPricePerHour(), createParkingLotDto.getContactPerson(), createParkingLotDto.getAddress(), createParkingLotDto.getDivision(), createParkingLotDto.getParkingCategory());
    }

    public ParkingLotDto toParkingLotDto(ParkingLot parkingLot) {
        return new ParkingLotDto(parkingLot.getId(), parkingLot.getName(), parkingLot.getMaxCapacity(), parkingLot.getPricePerHour(), parkingLot.getContactPerson(), parkingLot.getAddress(), parkingLot.getDivision(),parkingLot.getParkingCategory());
    }

    public Collection<ParkingLotDto> toParkingLotDto(Collection<ParkingLot> parkingLots) {
        return parkingLots.stream().map(this::toParkingLotDto).collect(Collectors.toList());
    }
}
