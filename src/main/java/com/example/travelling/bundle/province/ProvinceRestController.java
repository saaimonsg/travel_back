package com.example.travelling.bundle.province;

import com.example.travelling.bundle.province.data.ProvinceConstants;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.bundle.province.service.ProvinceService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/province")
@Slf4j

public class ProvinceRestController {

    private final ProvinceService provinceService;
    private final Gson gson;
    private final PlatformSecurityContext platformSecurityContext;

    public ProvinceRestController(ProvinceService provinceService, PlatformSecurityContext platformSecurityContext) {
        this.provinceService = provinceService;
        this.platformSecurityContext = platformSecurityContext;
        gson = new Gson();
    }

    @RequestMapping(value = "/{provinceId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findByCityId(@PathVariable(name = "provinceId") Long provinceId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(ProvinceConstants.PROVINCE_READ);

        return gson.toJson(provinceService.findById(provinceId));
    }
    @RequestMapping(value = "/country", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findProvinceByCountryId(@RequestParam(name = "countryId") Long countryId) throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(ProvinceConstants.PROVINCE_READ);
        return gson.toJson(provinceService.findAllByCountryId(countryId));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAll() {

        return gson.toJson(provinceService.findAll());

    }


    @RequestMapping(value = "/save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCity(@RequestBody String body) {
        Province province = gson.fromJson(body, Province.class);
        provinceService.create(province);
        return gson.toJson("city.create.successful");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCity(@RequestParam(name = "cityId") Long cityId) {
        provinceService.remove(cityId);
        return gson.toJson("city.remove.successful");
    }
}
