package com.example.travelling.bundle.appuser.domain;


import com.example.travelling.infra.security.exception.NoAuthorizationException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class AppUser  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_appuser_role", joinColumns = @JoinColumn(name = "appuser_id"), inverseJoinColumns
            = @JoinColumn(name = "role_id"))
    private Collection<Role> role;


    public AppUser(String name, String surname, String email, String username, String password,
                   Collection<Role> role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

//    public void validateHasReadPermission(final String resourceType) {
//        validateHasPermission("READ", resourceType);
//    }

//    private void validateHasPermission(final String prefix, final String resourceType) {
//        final String authorizationMessage = "User has no authority to " + prefix + " " + resourceType.toLowerCase() + "s";
//        final String matchPermission = prefix + "_" + resourceType.toUpperCase();
//
//        if (!hasNotPermissionForAnyOf("ALL_FUNCTIONS", "ALL_FUNCTIONS_READ", matchPermission)) {
//            return;
//        }
//
//        throw new NoAuthorizationException(authorizationMessage);
//    }
//    public boolean hasNotPermissionForAnyOf(final String... permissionCodes) {
//        boolean hasNotPermission = true;
//        for (final String permissionCode : permissionCodes) {
//            final boolean checkPermission = hasPermissionTo(permissionCode);
//            if (checkPermission) {
//                hasNotPermission = false;
//                break;
//            }
//        }
//        return hasNotPermission;
//    }
//    private boolean hasPermissionTo(final String permissionCode) {
//        boolean hasPermission = hasAllFunctionsPermission();
//        if (!hasPermission) {
//            for (final Role role : this.roles) {
//                if (role.hasPermissionTo(permissionCode)) {
//                    hasPermission = true;
//                    break;
//                }
//            }
//        }
//        return hasPermission;
//    }
//
//    private boolean hasAllFunctionsPermission() {
//        boolean match = false;
//        for (final Role role : this.roles) {
//            if (role.hasPermissionTo("ALL_FUNCTIONS")) {
//                match = true;
//                break;
//            }
//        }
//        return match;
//    }
}
