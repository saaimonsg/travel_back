package com.example.travelling.bundle.country.controller;

import com.example.travelling.bundle.country.data.CountryConstants;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.country.service.CountryService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/country")
public class CountryRestController {


    private final CountryService countryService;
    private final Gson gson;
    private final PlatformSecurityContext platformSecurityContext;

    @Autowired
    public CountryRestController(CountryService countryService, PlatformSecurityContext platformSecurityContext) {
        this.countryService = countryService;
        this.platformSecurityContext = platformSecurityContext;
        gson = new Gson();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> findAll() throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.READ_COUNTRY_PERMISSION);

        return ResponseEntity.ok(gson.toJson(countryService.findAll()));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> findById(@RequestParam(name = "countryId") Long countryId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.READ_COUNTRY_PERMISSION);
        return ResponseEntity.ok(gson.toJson(countryService.findById(countryId)));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody Country country) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.WRITE_COUNTRY_PERMISSION);

        return ResponseEntity.ok(gson.toJson(countryService.save(country)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long countryId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.DELETE_COUNTRY_PERMISSION);
        return ResponseEntity.ok(gson.toJson(countryService.delete(countryId)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody Country country) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.UPDATE_COUNTRY_PERMISSION);
        return ResponseEntity.ok(gson.toJson(countryService.update(country)));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> searchByName(@RequestParam(value = "pattern") String pattern) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.READ_COUNTRY_PERMISSION);
        return ResponseEntity.ok(gson.toJson(countryService.search(pattern)));
    }

}
