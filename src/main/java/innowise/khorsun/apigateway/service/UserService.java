package innowise.khorsun.apigateway.service;

import innowise.khorsun.apigateway.entity.User;
import innowise.khorsun.apigateway.repository.UserRepository;
import innowise.khorsun.apigateway.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void handleSuccessAuthentication(OAuth2AuthenticationToken oAuth2User) {
        OAuth2User oAuthUser = oAuth2User.getPrincipal();

        String email = oAuthUser.getAttribute("email");
        String name = oAuthUser.getAttribute("name");
        String familyName = oAuthUser.getAttribute("family_name");

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setLastName(familyName);
            //todo autorization
            user.setRole(Role.CUSTOMER);
            user.setPhoneNumber("test");
            user.setFirstName(name);
            user.setCreationDate(new Date());
        } else {
            user.setFirstName(name);
        }

        userRepository.save(user);
    }
}
