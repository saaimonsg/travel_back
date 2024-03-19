package com.example.travelling.infra.core.domain.appuser.controller;

import com.example.travelling.infra.core.domain.appuser.data.AppUserConstants;
import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.service.AppUserService;
import com.example.travelling.infra.security.data.AuthenticatedUserData;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;
    private final Gson gson;
    private final PlatformSecurityContext platformSecurityContext;

    @Autowired
    public AppUserController(AppUserService appUserService, PlatformSecurityContext platformSecurityContext) {
        this.appUserService = appUserService;
        this.platformSecurityContext = platformSecurityContext;
        gson = new Gson();
    }

    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String fetchAll() throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppUserConstants.READ_USER_PERMISSION);

        return gson.toJson(appUserService.findAll());
    }



    @RequestMapping(value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AppUserData fetchOne(@PathVariable long id) throws AppUserExceptionError, UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppUserConstants.READ_USER_PERMISSION);

        return appUserService.fetchOne(id);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AppUserData update(@RequestBody String body) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppUserConstants.UPDATE_CURRENT_USER_PERMISSION);
        AppUserData appUserData = gson.fromJson(body, AppUserData.class);
        appUserService.update(appUserData);
        return appUserData;
    }


    @RequestMapping(value = "/getCurrentUser",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCurrentUser() throws UnauthenticatedUserException, AppUserExceptionError {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppUserConstants.READ_CURRENT_USER_PERMISSIONS);
        AuthenticatedUserData authenticatedUserData = platformSecurityContext.authenticatedUser();
        authenticatedUserData.hasPermission(AppUserConstants.READ_USER_PERMISSION);
        return gson.toJson(appUserService.findByUsername(authenticatedUserData.getUsername()));
    }
}
