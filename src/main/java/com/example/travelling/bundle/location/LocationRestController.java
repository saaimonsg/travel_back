package com.example.travelling.bundle.location;

import com.example.travelling.bundle.location.constants.LocationApiConstants;
import com.example.travelling.bundle.location.country.service.CountryService;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/location")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class LocationRestController {
    private final CountryService countryService;
    private final PlatformSecurityContext platformSecurityContext;


    @Autowired
    public LocationRestController(CountryService countryService, PlatformSecurityContext platformSecurityContext) {
        this.countryService = countryService;
        this.platformSecurityContext = platformSecurityContext;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findByCountryId(@RequestParam(value = "countryId", required = false) Long countryId,
                                  @RequestParam(value = "provinceId", required = false) Long provinceId,
                                  @RequestParam(value = "cityId", required = false) Long cityId) throws UnAuthenticatedUserException {
//        this.platformSecurityContext.authenticatedUser().validateHasReadPermission(LocationApiConstants.LOCATION_RESOURCE_NAME);
        if ((countryId != null && countryId != 0) && (provinceId != null && provinceId != 0)) {
            return new Gson().toJson(countryService.findCityByCountryIdAndProvinceId(countryId,
                    provinceId));
        } else if (countryId != null && countryId != 0) {
            return new Gson().toJson(countryService.findByCountryId(countryId));
        } else {
            return new Gson().toJson(countryService.findAll());
        }
    }


}
