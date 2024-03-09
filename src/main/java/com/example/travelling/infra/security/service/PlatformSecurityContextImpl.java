package com.example.travelling.infra.security.service;

import com.example.travelling.infra.security.data.AuthenticatedUserData;
import com.example.travelling.infra.security.data.CustomUserDetails;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PlatformSecurityContextImpl implements PlatformSecurityContext {

    @Override
    public AuthenticatedUserData authenticatedUser() throws UnAuthenticatedUserException {
        CustomUserDetails currentUser = null;

        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication auth = context.getAuthentication();
            if (auth != null) {
                currentUser = (CustomUserDetails) auth.getPrincipal();
            }else{
                throw new UnAuthenticatedUserException();
            }
        }
        if (currentUser == null) {
            throw new UnAuthenticatedUserException();
        }
        AuthenticatedUserData authenticatedUserData = new AuthenticatedUserData(currentUser.getUsername(),
                currentUser.getPassword(), currentUser.getAuthorities(), null);

        return authenticatedUserData;
    }

}
