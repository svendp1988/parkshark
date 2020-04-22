package south.park.parkshark;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.domain.service.ParkingLotService;

@RestController
@RequestMapping(path = "/parkinglot")
public class ParkingLotController {

    ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        return parkingLotService.createParkingLot(createParkingLotDto);
    }


}
