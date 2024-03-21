package com.example.travelling.bundle.country.controller;

import com.example.travelling.bundle.country.data.CountryConstants;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.country.service.CountryService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.COUNTRY_READ);

        return ResponseEntity.ok(gson.toJson(countryService.findAll()));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> findById(@RequestParam(name = "countryId") Long countryId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.COUNTRY_READ);
        return ResponseEntity.ok(gson.toJson(countryService.findById(countryId)));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody Country country) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser();

        return ResponseEntity.ok(gson.toJson(countryService.save(country)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long countryId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(CountryConstants.COUNTRY_DELETE);

        return ResponseEntity.ok(gson.toJson(countryService.delete(countryId)));
    }

}
