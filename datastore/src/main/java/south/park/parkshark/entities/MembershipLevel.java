package south.park.parkshark.entities;

import javax.persistence.*;

@Entity
@Table(name = "membership_levels")
public class MembershipLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_level_id")
    private int membershipLevelId;
    @Enumerated
    @Column(name = "name")
    private MembershipLevels membershipLevel;

    public MembershipLevel() {
    }

    public int getMembershipLevelId() {
        return membershipLevelId;
    }

    public MembershipLevels getMembershipLevel() {
        return membershipLevel;
    }
}
