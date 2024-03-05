package com.example.travelling.infra.security.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserAuthenticatedData {
    private String username;
    private Collection<GrantedAuthority> permissions;

}
