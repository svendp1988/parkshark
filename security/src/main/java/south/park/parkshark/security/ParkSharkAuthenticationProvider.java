package south.park.parkshark.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import south.park.parkshark.security.authentication.ExternalAuthentication;
import south.park.parkshark.security.authentication.FakeAuthenticationService;
import south.park.parkshark.security.roles.Feature;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkSharkAuthenticationProvider implements AuthenticationProvider {
    FakeAuthenticationService authService;

    @Autowired
    public ParkSharkAuthenticationProvider(FakeAuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        ExternalAuthentication user = authService.getUser(username, password);
        if (user == null) {
            throw new BadCredentialsException("Username and password not found.");
        } else {
            return new UsernamePasswordAuthenticationToken(username, password, rolesToGrantedAuthorities(Feature.getFeaturesForRoles(user.getRoles())));
        }

    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<Feature> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
