package com.example.travelling.infra.security.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserAuthenticatedData {
    private String username;
    private String base64EncodedAuthenticationKey;

    public UserAuthenticatedData(String username, String base64EncodedAuthenticationKey) {
        this.username = username;
        this.base64EncodedAuthenticationKey = base64EncodedAuthenticationKey;
    }
}
