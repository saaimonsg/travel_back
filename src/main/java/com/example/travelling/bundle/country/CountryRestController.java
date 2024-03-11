package com.example.travelling.bundle.country;

import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.country.service.CountryService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/country")
public class CountryRestController {


    private final CountryService countryService;
    private final Gson gson = new Gson();

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAll() {
        return gson.toJson(countryService.findAll());
    }
    @RequestMapping(value = "get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findById(@RequestParam(name = "countryId") Long countryId) {
        return gson.toJson(countryService.findById(countryId));
    }
    @RequestMapping(value = "/save", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String find(@RequestBody Country country) {
        return gson.toJson(countryService.save(country));
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String find(@RequestParam Long countryId) {
        return gson.toJson(countryService.delete(countryId));
    }

}
