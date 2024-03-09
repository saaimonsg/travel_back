package com.example.travelling.infra.security.data;

import com.example.travelling.infra.core.domain.appuser.domain.Permission;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;


@Getter
@Setter
@ToString
public class AuthenticatedUserData {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Collection<Permission> permissions;

    public AuthenticatedUserData(String username, String password, Collection<? extends GrantedAuthority> authorities, Collection<Permission> permissions) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.permissions = permissions;
    }

    public boolean validatePermission(String locationResourceName) {
        return permissions.contains(new Permission(locationResourceName));
    }
}
