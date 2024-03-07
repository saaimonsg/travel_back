package com.example.travelling.bundle.appuser.controller;

import com.example.travelling.bundle.appuser.data.AppUserData;
import com.example.travelling.bundle.appuser.exception.AppUserExceptionError;
import com.example.travelling.bundle.appuser.service.AppUserService;
import com.example.travelling.bundle.appuser.domain.AppUser;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AppUser> fetchAll() {
        return appUserService.fetchAll();
    }

    @RequestMapping(name = "save", value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String save(@RequestBody AppUserData appUser) throws AppUserExceptionError {

        return new Gson().toJson(appUserService.save(appUser));
    }
    @RequestMapping(value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AppUserData fetchOne(@PathVariable long id) throws AppUserExceptionError {
        return  appUserService.fetchOne(id);
    }


}
