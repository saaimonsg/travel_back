package com.example.travelling.infra.security.service;

import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import com.example.travelling.infra.core.domain.role.RoleJpaRepository;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.permission.Permission;
import com.example.travelling.infra.core.domain.role.Role;
import com.example.travelling.infra.security.data.AuthenticatedUserData;
import com.example.travelling.infra.security.data.CustomUserDetails;
import com.example.travelling.infra.core.domain.permission.PermissionJpaRepository;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlatformSecurityContextImpl implements PlatformSecurityContext {

    private final RoleJpaRepository roleJpaRepository;
    private final AppUserJpaRepository appUserJpaRepository;
    private final PermissionJpaRepository permissionJpaRepository;
    @Override
    public AuthenticatedUserData authenticatedUser() throws UnauthenticatedUserException {
        CustomUserDetails currentUser = null;

        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication auth = context.getAuthentication();
            if (auth != null) {
                currentUser = (CustomUserDetails) auth.getPrincipal();
            }else{
                throw new UnauthenticatedUserException("unauthenticated");
            }
        }
        if (currentUser == null) {
            throw new UnauthenticatedUserException("user.not.logged");
        }
        AppUser appUser = appUserJpaRepository.findByUsername(currentUser.getUsername());
        List<Permission> permissions = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            Role role1 = roleJpaRepository.findByName(role.getName());
            role1.getPermissions().forEach(permission -> {
                permissions.add(new Permission(permission.getResourceName()));
            });
        });
        AuthenticatedUserData authenticatedUserData = new AuthenticatedUserData(currentUser.getUsername(),
                null, currentUser.getAuthorities(),permissions);

        return authenticatedUserData;
    }

}
