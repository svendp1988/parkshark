package south.park.parkshark.security.roles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Feature {
    MEMBER_ONLY(List.of(ParksharkRole.MEMBER)),
    MANAGER_ONLY(List.of(ParksharkRole.MANAGER));


    private List<ParksharkRole> roles;

    Feature(List<ParksharkRole> roleList) {
        this.roles = roleList;
    }

    public List<ParksharkRole> getRoles() {
        return roles;
    }

    public static List<Feature> getFeaturesForRoles(List<ParksharkRole> rolesOfUser) {

        List<Feature> listOfAllFeatures = List.of(Feature.values());
        List<Feature> allowedFeatures = new ArrayList<>();
        for (Feature feature : listOfAllFeatures) {
            if (!Collections.disjoint(feature.getRoles(), rolesOfUser)) {
                allowedFeatures.add(feature);
            }
        }
        return allowedFeatures;
    }


}