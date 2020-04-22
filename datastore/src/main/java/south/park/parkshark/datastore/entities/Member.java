package south.park.parkshark.datastore.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Enumerated(EnumType.STRING)
    private MembershipLevels membershipLevel;
    @Column(name = "registration_date")
    LocalDate registrationDate;

    @OneToMany
    @JoinTable(name = "member_plates",
    joinColumns = {@JoinColumn(name = "member_id")},
    inverseJoinColumns = {@JoinColumn(name = "license_plate_id")})
    List<LicensePlate> licensePlates = new ArrayList<>();

    public Member() {
    }

    public Member(Person person, LocalDate registrationDate, List<LicensePlate> licensePlates) {
        this(0, person, MembershipLevels.BRONZE, registrationDate, licensePlates);
    }

    public Member(Person person, MembershipLevels membershipLevel, LocalDate registrationDate,
                  List<LicensePlate> licensePlates){
        this(0, person, membershipLevel, registrationDate, licensePlates);
    }

    public Member(long memberId, Person person, MembershipLevels membershipLevel, LocalDate registrationDate,
                  List<LicensePlate> licensePlates) {
        this.memberId = memberId;
        this.person = person;
        this.membershipLevel = membershipLevel;
        this.registrationDate = registrationDate;
        this.licensePlates = licensePlates;
    }

    public long getMemberId() {
        return memberId;
    }

    public Person getPerson() {
        return person;
    }

    public MembershipLevels getMembershipLevel() {
        return membershipLevel;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public List<LicensePlate> getLicensePlates() {
        return licensePlates;
    }

    @Override
    public String toString() {
        return "members{" +
                "memberId=" + memberId +
                ", personId=" + person +
                ", membershipLevel=" + membershipLevel +
                ", registrationId=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
