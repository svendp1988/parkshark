package south.park.parkshark.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import south.park.parkshark.domain.dto.response.DivisionDto;
import south.park.parkshark.domain.service.DivisionService;

import java.util.Collection;


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

    @PreAuthorize("hasAuthority('MANAGER_ONLY')")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<DivisionDto> getAllDivisions() {
        return divisionService.getAllDivisions();
    }


}
