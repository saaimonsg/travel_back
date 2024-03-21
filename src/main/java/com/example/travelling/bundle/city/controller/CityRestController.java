package com.example.travelling.bundle.city.controller;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.city.service.CityService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/city")
public class CityRestController {

    private final CityService cityService;
    private final Gson gson = new Gson();
    private final PlatformSecurityContext platformSecurityContext;

    @Autowired
    public CityRestController(CityService cityService, PlatformSecurityContext platformSecurityContext) {
        this.cityService = cityService;
        this.platformSecurityContext = platformSecurityContext;
    }
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findByProvinceId(@RequestParam(name = "provinceId", required = false) Long provinceId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CityConstants.READ_CITY_PERMISSIONS);
        return gson.toJson(cityService.findAllByProvinceId(provinceId));

    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAll() throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CityConstants.READ_CITY_PERMISSIONS);
        return gson.toJson(cityService.findAll());

    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCity(@RequestBody String body) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CityConstants.WRITE_CITY_PERMISSIONS);
        City cityData = gson.fromJson(body, City.class);
        cityService.create(cityData);
        return gson.toJson("city.create.successful");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCity(@RequestParam(name = "cityId") Long cityId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CityConstants.DELETE_CITY_PERMISSIONS);
        cityService.remove(cityId);
        return gson.toJson("city.remove.successful");
    }
}
