package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import south.park.parkshark.domain.dto.response.DivisionDto;
import south.park.parkshark.entities.Division;
import south.park.parkshark.mappers.DivisionMapper;
import south.park.parkshark.repositories.DivisionRepository;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DivisionService {

    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }


    public DivisionDto createDivision(CreateDivisionDto createDivisionDto) {
        Division savedDivision = divisionRepository.save(divisionMapper.toDivision(createDivisionDto));
        return divisionMapper.toDivisionDto(savedDivision);
    }

    public Collection<DivisionDto> getAllDivisions() {
        List<Division> divisionList = divisionRepository.findAll();
        return divisionMapper.toDivisionDto(divisionList);
    }
}