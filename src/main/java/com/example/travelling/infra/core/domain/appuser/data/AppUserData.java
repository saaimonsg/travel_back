package com.example.travelling.infra.core.domain.appuser.data;

import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.role.Role;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AppUserData {
    private Long id;
    private String username;
    private String password;
    private String password2;
    private String name;
    private String surname;
    private String email;
    private boolean isDriver;
    private Collection<Role> roles;


    public AppUserData(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.isDriver = user.isDriver();
        this.roles = user.getRoles();
    }

    public AppUserData() {
        //
    }
}
