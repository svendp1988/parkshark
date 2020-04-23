package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.datastore.entities.ContactData;
import south.park.parkshark.datastore.repositories.*;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.datastore.entities.ParkingLot;
import south.park.parkshark.datastore.entities.Person;
import south.park.parkshark.domain.mappers.ParkingLotMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final  ParkingLotMapper parkingLotMapper;
    private final  ValidationService validationService;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private  final DivisionRepository divisionRepository;
    private final ParkingCategoryRepository parkingCategoryRepository;
    private final ContactDataRepository contactDataRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper,
                             ValidationService validationService, AddressRepository addressRepository,
                             PersonRepository personRepository, DivisionRepository divisionRepository,
                             ParkingCategoryRepository parkingCategoryRepository, ContactDataRepository contactDataRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
        this.validationService = validationService;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.divisionRepository = divisionRepository;
        this.parkingCategoryRepository = parkingCategoryRepository;
        this.contactDataRepository = contactDataRepository;
    }

    public ParkingLotDto createParkingLot(CreateParkingLotDto createParkingLotDto) {
        validationService.assertAtLeastOneEmailExistsAndAllAreValid(createParkingLotDto);
        validationService.assertAtLeastOnePhoneNumberIsGiven(createParkingLotDto);


        Person contactPerson = createParkingLotDto.getContactPerson();
        contactPerson.setAddress(addressRepository.save(contactPerson.getAddress()));
        createParkingLotDto.setAddress(addressRepository.save(createParkingLotDto.getAddress()));
        createParkingLotDto.setContactPerson(personRepository.save(contactPerson));
        System.out.println("ID: " + createParkingLotDto.getContactPerson().getPersonId());
        List<ContactData> contactData = createParkingLotDto.getContactPerson().getContactDataList();
        Iterable<ContactData> contactDataIterable= contactDataRepository.saveAll(contactData);
        ArrayList<ContactData> returnedContacts = new ArrayList<>();
        contactDataIterable.iterator().forEachRemaining(returnedContacts::add);
        createParkingLotDto.getContactPerson().setContactData(returnedContacts);
        createParkingLotDto.setDivision(divisionRepository.save(createParkingLotDto.getDivision()));
        createParkingLotDto.setParkingCategory(parkingCategoryRepository.save(createParkingLotDto.getParkingCategory()));

        ParkingLot parkingLot = parkingLotRepository.save(parkingLotMapper.toParkingLot(createParkingLotDto));
        return parkingLotMapper.toParkingLotDto(parkingLot);
    }

    public Collection<ParkingLotDto> getAllParkingLots () {
        return parkingLotMapper.toParkingLotDto( parkingLotRepository.findAll());
    }
}
