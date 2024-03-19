package com.example.travelling.infra.security.controller;

import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.service.AppUserService;
import com.example.travelling.infra.security.data.UserAuthenticatedData;
import com.example.travelling.infra.security.data.UserAuthenticationData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private final AppUserService appUserService;
    private final Gson gson;

    @Autowired
    public AuthenticationApiController(AuthenticationManager authenticationManager, AppUserJpaRepository userRepository, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.appUserService = appUserService;
        gson = new Gson();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String authenticate(@RequestBody String jsonString) throws AppUserExceptionError {

        UserAuthenticationData request = new Gson().fromJson(jsonString, UserAuthenticationData.class);

        if (request == null) {
            return "No user authentication data provided";
        }
        //TODO here we should validate the user data
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername().toLowerCase(),
                        request.getPassword());
        Authentication authenticated = this.authenticationManager.authenticate(authenticationRequest);

        AppUserData user = appUserService.findByUsername(request.getUsername());

        final byte[] base64EncodedAuthenticationKey = Base64.getEncoder()
                .encode((request.getUsername() + ":" + request.getPassword()).getBytes(StandardCharsets.UTF_8));

        UserAuthenticatedData userAuthenticatedData =
                new UserAuthenticatedData(
                        new String(base64EncodedAuthenticationKey, StandardCharsets.UTF_8),
                        user
                );
        if (authenticated.isAuthenticated()) {
            //FIXME
            SecurityContext context = SecurityContextHolder.getContext();

            context.setAuthentication(authenticated);
            return gson.toJson(userAuthenticatedData);
        }
        return gson.toJson(new UserAuthenticatedData(null, null));

    }

    @RequestMapping(name = "Register", value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HttpStatus> save(@RequestBody AppUserData appUser) throws AppUserExceptionError {
        appUserService.registerUser(appUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}