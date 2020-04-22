package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.entities.ParkingLot;
import south.park.parkshark.mappers.ParkingLotMapper;
import south.park.parkshark.repositories.ParkingLotRepository;

import java.util.Collection;

@Service
@Transactional
public class ParkingLotService {

    ParkingLotRepository parkingLotRepository;
    ParkingLotMapper parkingLotMapper;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
    }

    public ParkingLotDto createParkingLot(CreateParkingLotDto createParkingLotDto) {
        ParkingLot parkingLot = parkingLotRepository.save(parkingLotMapper.toParkingLot(createParkingLotDto));
        return parkingLotMapper.toParkingLotDto(parkingLot);
    }

    public Collection<ParkingLotDto> getAllParkingLots () {
        return parkingLotMapper.toParkingLotDto( parkingLotRepository.findAll());
    }
}
