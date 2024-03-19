package com.example.travelling.infra.security.data;

import com.example.travelling.infra.core.domain.permission.Permission;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;


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

    public boolean hasPermission(String locationResourceName) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Permission o = new Permission(locationResourceName);
        for (Permission permission : permissions) {
            if (permission.getResourceName().equals(o.getResourceName())) {
                flag.set(true);
                break;
            }
        }
        if(flag.get() == false){
            throw new RuntimeException("You do not have permission to access this resource");
        }
        return flag.get();
    }
}

