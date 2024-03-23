package com.example.travelling.infra.core.domain.permission;

import com.example.travelling.infra.core.domain.permission.service.PermissionService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/permissions")
public class AppPermissionRestController {

    private Gson gson = new Gson();
    private final PlatformSecurityContext platformSecurityContext;
    private final PermissionService permissionService;


    @Autowired
    public AppPermissionRestController(PlatformSecurityContext platformSecurityContext, PermissionService permissionService) {
        this.platformSecurityContext = platformSecurityContext;
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() throws UnauthenticatedUserException {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppPermissionConstants.READ_PERMISSIONS_PERMISSION);
        return gson.toJson(permissionService.findAll());
    }

    @RequestMapping(value = "create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Permission permission) throws UnauthenticatedUserException, Exception {
        this.platformSecurityContext.authenticatedUser().hasPermission(AppPermissionConstants.CREATE_PERMISSIONS_PERMISSION);

        return gson.toJson(permissionService.save(permission));
    }

}
