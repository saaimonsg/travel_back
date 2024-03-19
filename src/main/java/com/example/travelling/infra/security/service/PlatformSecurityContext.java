package com.example.travelling.infra.security.service;

import com.example.travelling.infra.security.data.AuthenticatedUserData;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;

public interface PlatformSecurityContext {

    AuthenticatedUserData authenticatedUser() throws UnauthenticatedUserException;

}
