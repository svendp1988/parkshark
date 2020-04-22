package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.entities.ParkingLot;
import south.park.parkshark.entities.Person;
import south.park.parkshark.mappers.ParkingLotMapper;
import south.park.parkshark.repositories.*;

import java.util.Collection;

@Service
@Transactional
public class ParkingLotService {

    private final  ParkingLotRepository parkingLotRepository;
    private final  ParkingLotMapper parkingLotMapper;
    private final  ValidationService validationService;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private  final DivisionRepository divisionRepository;
    private final ParkingCategoryRepository parkingCategoryRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper, ValidationService validationService, AddressRepository addressRepository, PersonRepository personRepository, DivisionRepository divisionRepository, ParkingCategoryRepository parkingCategoryRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
        this.validationService = validationService;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.divisionRepository = divisionRepository;
        this.parkingCategoryRepository = parkingCategoryRepository;
    }

    public ParkingLotDto createParkingLot(CreateParkingLotDto createParkingLotDto) {
        validationService.assertAtLeastOneEmailExistsAndAllAreValid(createParkingLotDto);
        validationService.assertAtLeastOnePhoneNumberIsGiven(createParkingLotDto);


        Person contactPerson = createParkingLotDto.getContactPerson();
        contactPerson.setAddress(addressRepository.save(contactPerson.getAddress()));
        createParkingLotDto.setAddress(addressRepository.save(createParkingLotDto.getAddress()));
        createParkingLotDto.setContactPerson(personRepository.save(contactPerson));
        createParkingLotDto.setDivision(divisionRepository.save(createParkingLotDto.getDivision()));
        createParkingLotDto.setParkingCategory(parkingCategoryRepository.save(createParkingLotDto.getParkingCategory()));
        ParkingLot parkingLot = parkingLotRepository.save(parkingLotMapper.toParkingLot(createParkingLotDto));
        return parkingLotMapper.toParkingLotDto(parkingLot);
    }

    public Collection<ParkingLotDto> getAllParkingLots () {
        return parkingLotMapper.toParkingLotDto( parkingLotRepository.findAll());
    }
}
