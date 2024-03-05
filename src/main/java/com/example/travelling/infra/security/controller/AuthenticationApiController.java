package com.example.travelling.infra.security.controller;

import com.example.travelling.appuser.AppUserRepository;
import com.example.travelling.appuser.AppUser;
import com.example.travelling.infra.security.data.UserAuthenticatedData;
import com.example.travelling.infra.security.data.UserAuthenticationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationApiController {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;

    @Autowired
    public AuthenticationApiController(AuthenticationManager authenticationManager, AppUserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody UserAuthenticationData userAuthenticationData) {


        if (userAuthenticationData == null) {
            return "No user authentication data provided";
        }
        //TODO here we should validate the user data
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userAuthenticationData.getUsername(), userAuthenticationData.getPassword());
        Authentication authenticated = this.authenticationManager.authenticate(authenticationRequest);

        final Collection<GrantedAuthority> permissions = new ArrayList<>();
        AppUser user = userRepository.findByUsername(userAuthenticationData.getUsername());
        String role = user.getRole();
        permissions.add(new SimpleGrantedAuthority(role));
        UserAuthenticatedData userAuthenticatedData = new UserAuthenticatedData(userAuthenticationData.getUsername(), permissions);
        if (authenticated.isAuthenticated()) {
            //TODO here the securityContextHolder should save the authenticated user data for the session
            //but it isn't
            SecurityContextHolder.createEmptyContext().setAuthentication(authenticated);
            return userAuthenticatedData.toString();
        }
        return "Controller Authentication failed";

    }
}
