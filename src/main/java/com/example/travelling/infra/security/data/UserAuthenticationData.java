package com.example.travelling.infra.security.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticationData {

    private String username;
    private String password;

}
