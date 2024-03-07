package com.example.travelling.infra.security.service;

import com.example.travelling.bundle.appuser.domain.AppUser;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PlatformSecurityContextImpl implements PlatformSecurityContext {
    @Override
    public AppUser authenticatedUser() throws UnAuthenticatedUserException {
        AppUser currentUser = null;
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication auth = context.getAuthentication();
            if (auth != null) {
                currentUser = (AppUser) auth.getPrincipal();
            }
        }

        if (currentUser == null) {
            throw new UnAuthenticatedUserException();
        }

//        if (this.doesPasswordHasToBeRenewed(currentUser)) {
//            throw new ResetPasswordException(currentUser.getId());
//        }

        return currentUser;
    }

}
