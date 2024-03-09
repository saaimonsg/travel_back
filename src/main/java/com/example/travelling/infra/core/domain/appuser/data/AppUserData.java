package com.example.travelling.infra.core.domain.appuser.data;

import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.domain.Role;
import lombok.*;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class AppUserData {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean isDriver;

    private Collection<Role> roles;


    public AppUserData(AppUser user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }


}
