package com.example.travelling.bundle.appuser.domain;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.infra.core.domain.AbstractPersistableCustom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "app_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" },
        name = "unq_name") })
@Getter
@Setter
public class Role extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    protected Role() {

    }

    public Role(final String name, final String description) {
        this.name = name.trim();
        this.description = description.trim();
        this.disabled = false;
    }


}

