package com.example.travelling.bundle.city;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.city.service.CityService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/city")
public class CityRestController {

    private final CityService cityService;
    private final Gson gson = new Gson();
    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAll(@RequestParam(name = "provinceId") Long provinceId) {
        if(provinceId == null || provinceId == 0){
            return gson.toJson(cityService.findAll());
        }else{
            return gson.toJson(cityService.findAllByProvinceId(provinceId));
        }
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findByCityId(@PathVariable(name = "cityId") Long cityId){
        return gson.toJson(cityService.findById(cityId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCity(@RequestBody String body){
        City cityData = gson.fromJson(body, City.class);
        cityService.create(cityData);
        return gson.toJson("city.create.successful");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCity(@RequestParam(name = "cityId") Long cityId){
        cityService.remove(cityId);
        return gson.toJson("city.remove.successful");
    }
}
