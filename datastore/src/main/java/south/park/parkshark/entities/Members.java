package south.park.parkshark.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Members {
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

    public Members() {
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

    @Override
    public String toString() {
        return "members{" +
                "memberId=" + memberId +
                ", personId=" + person +
                ", membershipLevel=" + membershipLevel +
                ", registrationId=" + registrationDate +
                '}';
    }
}
