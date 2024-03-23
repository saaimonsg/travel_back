package com.example.travelling.infra.core.domain.role;

import com.example.travelling.infra.core.domain.role.RoleConstants;
import com.example.travelling.infra.core.domain.role.RoleData;
import com.example.travelling.infra.core.domain.role.RoleExceptionError;
import com.example.travelling.infra.core.domain.role.RoleService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class AppRoleRestController {

    private final PlatformSecurityContext securityContext;
    private final RoleService roleService;
    private Gson gson;

    @Autowired
    public AppRoleRestController(PlatformSecurityContext securityContext, RoleService roleService) {
        this.securityContext = securityContext;
        this.roleService = roleService;
        gson = new Gson();
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() throws UnauthenticatedUserException {
        this.securityContext.authenticatedUser().hasPermission(RoleConstants.READ_ROLE_PERMISSION);
        return gson.toJson(roleService.findAll());
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get(@RequestParam(value = "roleId", required = false) Long roleId) throws UnauthenticatedUserException {
        this.securityContext.authenticatedUser().hasPermission(RoleConstants.READ_ROLE_PERMISSION);
        return gson.toJson(roleService.findById(roleId));
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createRole(@RequestBody RoleData roleData) throws RoleExceptionError, UnauthenticatedUserException {
        this.securityContext.authenticatedUser().hasPermission(RoleConstants.WRITE_ROLE_PERMISSION);
        if (roleData.getName() == "" || roleData.getName() == null) {
            throw new RoleExceptionError("role.empty.name");
        }
        return gson.toJson(roleService.save(roleData));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editRole(@RequestBody RoleData roleData) throws UnauthenticatedUserException {
        this.securityContext.authenticatedUser().hasPermission(RoleConstants.UPDATE_ROLE_PERMISSION);
        return gson.toJson(roleService.edit(roleData));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteRole(@RequestParam(value = "roleId") Long roleId) throws UnauthenticatedUserException {
        this.securityContext.authenticatedUser().hasPermission(RoleConstants.DELETE_ROLE_PERMISSION);
        return gson.toJson(roleService.delete(roleId));
    }


}
