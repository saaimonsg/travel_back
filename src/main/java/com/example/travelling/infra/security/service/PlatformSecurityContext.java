package com.example.travelling.infra.security.service;

import com.example.travelling.bundle.appuser.domain.AppUser;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;

public interface PlatformSecurityContext {

    AppUser authenticatedUser() throws UnAuthenticatedUserException;

}
