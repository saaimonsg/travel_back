package com.example.travelling.appuser;

import lombok.*;

@Data
@RequiredArgsConstructor
public class AppUserData {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean isDriver;
    private String role;


    public AppUserData(AppUser user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
