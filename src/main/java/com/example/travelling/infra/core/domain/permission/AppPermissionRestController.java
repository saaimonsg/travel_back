package com.example.travelling.infra.core.domain.permission;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/permissions")
public class AppPermissionRestController {

    private final PermissionJpaRepository permissionJpaRepository;
    private Gson gson = new Gson();

    @Autowired
    public AppPermissionRestController(PermissionJpaRepository permissionJpaRepository) {
        this.permissionJpaRepository = permissionJpaRepository;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        return gson.toJson(permissionJpaRepository.findAll());
    }

}
