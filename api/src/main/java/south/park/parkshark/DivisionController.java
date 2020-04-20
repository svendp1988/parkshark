package south.park.parkshark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import south.park.parkshark.dtos.CreateDivisionDto;
import south.park.parkshark.dtos.DivisionDto;


@RestController
@RequestMapping(path = "/divisions")
public class DivisionController {


    DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;

    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        return divisionService.createDivision(createDivisionDto);
    }


}
