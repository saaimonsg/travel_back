package com.example.travelling.infra.security.controller;

import com.example.travelling.bundle.appuser.data.AppUserJpaRepository;
import com.example.travelling.bundle.appuser.domain.AppUser;
import com.example.travelling.bundle.appuser.domain.Role;
import com.example.travelling.infra.security.data.UserAuthenticatedData;
import com.example.travelling.infra.security.data.UserAuthenticationData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

@RestController
@RequestMapping("/authenticate")
@Slf4j
public class AuthenticationApiController {
    private final AuthenticationManager authenticationManager;
    private final AppUserJpaRepository userRepository;
    private final Gson gson;

    @Autowired
    public AuthenticationApiController(AuthenticationManager authenticationManager, AppUserJpaRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        gson = new Gson();
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody String jsonString) {

        UserAuthenticationData request = new Gson().fromJson(jsonString, UserAuthenticationData.class);

        if (request == null) {
            return "No user authentication data provided";
        }
        //TODO here we should validate the user data
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        Authentication authenticated = this.authenticationManager.authenticate(authenticationRequest);

        AppUser user = userRepository.findByUsername(request.getUsername());

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
        });

        final byte[] base64EncodedAuthenticationKey = Base64.getEncoder()
                .encode((request.getUsername() + ":" + request.getPassword()).getBytes(StandardCharsets.UTF_8));

        UserAuthenticatedData userAuthenticatedData = new UserAuthenticatedData(request.getUsername(),
                grantedAuthorities, new String(base64EncodedAuthenticationKey, StandardCharsets.UTF_8));
        if (authenticated.isAuthenticated()) {
            //FIXME
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return gson.toJson(userAuthenticatedData);
        }
        return gson.toJson(new UserAuthenticatedData());

    }
}
