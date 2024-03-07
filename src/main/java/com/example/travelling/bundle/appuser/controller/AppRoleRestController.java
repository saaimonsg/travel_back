package com.example.travelling.bundle.appuser.controller;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.data.RoleJpaRepository;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class AppRoleRestController {

    private final RoleJpaRepository repository;
    private Gson gson;

    public AppRoleRestController(RoleJpaRepository repository) {
        this.repository = repository;
        gson = new Gson();
    }

    @GetMapping
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        return gson.toJson(repository.findAll());
    }

    @GetMapping
    @RequestMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public String createRole(@RequestBody RoleData roleData) {
        return gson.toJson(repository.findAll());
    }

}
