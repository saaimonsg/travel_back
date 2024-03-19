package com.example.travelling.infra.core.domain.role;

import com.example.travelling.infra.core.domain.permission.Permission;
import com.example.travelling.infra.core.jpa.AbstractPersistableCustom;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "auth_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" },
        name = "unq_name") })
@Data
@NoArgsConstructor
public class Role extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_auth_role_auth_permission",
     joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name="permission_id"))
    Collection<Permission> permissions;

    public Role(String name, String description, Boolean disabled) {
        this.name = name;
        this.description = description;
        this.disabled = disabled;
    }


}

