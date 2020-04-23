package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.exceptions.InvalidEmailException;
import south.park.parkshark.domain.exceptions.LackingEmailAddressException;
import south.park.parkshark.domain.exceptions.LackingPhoneNumberException;
import south.park.parkshark.datastore.entities.ContactData;
import south.park.parkshark.datastore.entities.ContactTypes;
import south.park.parkshark.domain.mappers.MemberMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ValidationService {

    private final MemberMapper memberMapper;

    @Autowired
    public ValidationService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void assertAtLeastOnePhoneNumberIsGiven(List<ContactData> input) {
        if(input.stream()
                .filter(cd -> cd.getContactType() == ContactTypes.FIXEDPHONE || cd.getContactType() == ContactTypes.MOBILEPHONE)
                .collect(Collectors.toUnmodifiableList()).size() == 0){
            throw new LackingPhoneNumberException();
        }
    }

    public void assertAtLeastOnePhoneNumberIsGiven(CreateMemberDto input) {
      assertAtLeastOnePhoneNumberIsGiven(input.getContactData().stream()
              .map(cd -> memberMapper.toContactData(cd, 0)).collect(Collectors.toList()));
    }

    public void assertAtLeastOnePhoneNumberIsGiven(CreateParkingLotDto input) {
       assertAtLeastOnePhoneNumberIsGiven(input.getContactPerson().getContactDataList());
        }

    public void assertAtLeastOneEmailExistsAndAllAreValid(List<ContactData> input) {
        List<ContactData> emails = input.stream().filter(cd -> cd.getContactType() == ContactTypes.EMAIL).collect(Collectors.toUnmodifiableList());

        if(emails.size() == 0) throw new LackingEmailAddressException();
        assertEmailsHaveValidForm(emails);
    }
    public void assertAtLeastOneEmailExistsAndAllAreValid(CreateMemberDto input) {
        assertAtLeastOneEmailExistsAndAllAreValid(input.getContactData().stream()
                .map(cd-> memberMapper.toContactData(cd, 0)).collect(Collectors.toList()));
    }

    public void assertAtLeastOneEmailExistsAndAllAreValid(CreateParkingLotDto input) {
        assertAtLeastOneEmailExistsAndAllAreValid(input.getContactPerson().getContactDataList());

    }

    private void assertEmailsHaveValidForm(List<ContactData> emails) {
        String emailMatch = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        emails.forEach(cd -> { if(!cd.getData().matches(emailMatch)) throw new InvalidEmailException();});
    }
}
