package south.park.parkshark.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class members {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @Column(name = "person_id")
    private long personId;
    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel;
    @Column(name = "registration_id")
    LocalDate registrationId;

    public members() {
    }

    public long getMemberId() {
        return memberId;
    }

    public long getPersonId() {
        return personId;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public LocalDate getRegistrationId() {
        return registrationId;
    }

    @Override
    public String toString() {
        return "members{" +
                "memberId=" + memberId +
                ", personId=" + personId +
                ", membershipLevel=" + membershipLevel +
                ", registrationId=" + registrationId +
                '}';
    }
}
