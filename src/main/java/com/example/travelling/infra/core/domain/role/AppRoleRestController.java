package com.example.travelling.infra.core.domain.role;

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


    @RequestMapping(value = "/save", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createRole(@RequestBody RoleData roleData) throws RoleExceptionError {
        if(roleData.getName()== "" ||roleData.getName() == null){
            throw new RoleExceptionError("role.empty.name");
        }
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
