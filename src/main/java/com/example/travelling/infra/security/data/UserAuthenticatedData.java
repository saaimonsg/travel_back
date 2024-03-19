package com.example.travelling.infra.security.data;


import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserAuthenticatedData {
    private String base64EncodedAuthenticationKey;
    private AppUserData user;
    public UserAuthenticatedData(String base64EncodedAuthenticationKey,AppUserData user) {
        this.user = user;
        this.base64EncodedAuthenticationKey = base64EncodedAuthenticationKey;
    }
}
