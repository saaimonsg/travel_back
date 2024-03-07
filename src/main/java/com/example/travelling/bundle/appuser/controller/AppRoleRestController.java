package com.example.travelling.bundle.appuser.controller;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.data.RoleJpaRepository;
import com.example.travelling.bundle.appuser.service.RoleService;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class AppRoleRestController {


    private final RoleService roleService;
    private Gson gson;

    public AppRoleRestController( RoleService roleService) {
        this.roleService = roleService;
        gson = new Gson();
    }

    @GetMapping
    @RequestMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        return gson.toJson(roleService.findAll());
    }

    @GetMapping
    @RequestMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public String createRole(@RequestBody RoleData roleData) {
        return gson.toJson(roleService.create(roleData));
    }

}
