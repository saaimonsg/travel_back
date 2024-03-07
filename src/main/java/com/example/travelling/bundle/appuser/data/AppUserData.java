package com.example.travelling.bundle.appuser.data;

import com.example.travelling.bundle.appuser.domain.AppUser;
import com.example.travelling.bundle.appuser.domain.Role;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class AppUserData {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean isDriver;
    private Collection<Role> role;


    public AppUserData(AppUser user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
