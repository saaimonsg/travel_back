package com.example.travelling.bundle.appuser.controller;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.service.RoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class AppRoleRestController {


    private final RoleService roleService;
    private Gson gson;

    @Autowired
    public AppRoleRestController(RoleService roleService) {
        this.roleService = roleService;
        gson = new Gson();
    }

    @RequestMapping(value= "",method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get(@RequestParam(value = "roleId", required = false) Long roleId) {
        if (roleId != null) {
            return gson.toJson(roleService.findById(roleId));
        } else {
            return gson.toJson(roleService.findAll());
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createRole(@RequestBody RoleData roleData) {
        return gson.toJson(roleService.save(roleData));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editRole(@RequestBody RoleData roleData) {
        return gson.toJson(roleService.edit(roleData));
    }


}
