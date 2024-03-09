package com.example.travelling.infra.security.service;

import com.example.travelling.infra.security.data.AuthenticatedUserData;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;

public interface PlatformSecurityContext {

    AuthenticatedUserData authenticatedUser() throws UnAuthenticatedUserException;

}
