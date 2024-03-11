package com.example.travelling.bundle.province;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.bundle.province.service.ProvinceService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/province")
@Slf4j
@RequiredArgsConstructor
public class ProvinceRestController {

    private final ProvinceService provinceService;
    private final Gson gson = new Gson();

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findAll(@RequestParam(name = "countryId") Long countryId) {
        if(countryId == null || countryId == 0){
            return gson.toJson(provinceService.findAll());
        }else{
            return gson.toJson(provinceService.findAllByCountryId(countryId));
        }
    }

    @RequestMapping(value = "/{provinceId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findByCityId(@PathVariable(name = "provinceId") Long provinceId){
        return gson.toJson(provinceService.findById(provinceId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCity(@RequestBody String body){
        Province province = gson.fromJson(body, Province.class);
        provinceService.create(province);
        return gson.toJson("city.create.successful");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCity(@RequestParam(name = "cityId") Long cityId){
        provinceService.remove(cityId);
        return gson.toJson("city.remove.successful");
    }
}
