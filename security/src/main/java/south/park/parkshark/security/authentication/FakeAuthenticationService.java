package south.park.parkshark.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.park.parkshark.security.roles.ParksharkRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeAuthenticationService {

    private List<ExternalAuthentication> externalAuthentications;

    @Autowired
    public FakeAuthenticationService(List<ExternalAuthentication> externalAuthentications) {
        this.externalAuthentications = new ArrayList<>();
        this.externalAuthentications.add(ExternalAuthentication.externalAuthentication().withUsername("member").withPassword("member").withRoles(List.of(ParksharkRole.MEMBER)));
        this.externalAuthentications.add(ExternalAuthentication.externalAuthentication().withUsername("manager").withPassword("manager").withRoles(List.of(ParksharkRole.MANAGER)));
    }

    public ExternalAuthentication getUser(String username, String password) {
        return externalAuthentications.stream()
                .filter(externalAuthentication -> externalAuthentication.getUsername().equals(username))
                .filter(externalAuthentication -> externalAuthentication.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void addUser(String name, String password, List<ParksharkRole> roles) {
        if (externalAuthentications.stream().map(ExternalAuthentication::getUsername).collect(Collectors.toList()).contains(name)) {
            throw new IllegalArgumentException("username already exists");
        }
        externalAuthentications.add(ExternalAuthentication.externalAuthentication().withUsername(name).withPassword(password).withRoles(roles));
    }
}
