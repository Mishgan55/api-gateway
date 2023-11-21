package innowise.khorsun.apigateway.controller;

import innowise.khorsun.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private final UserService userService;
    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }
//
    @GetMapping("/save")
    public void currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        userService.handleSuccessAuthentication(oAuth2AuthenticationToken);
    }
}
