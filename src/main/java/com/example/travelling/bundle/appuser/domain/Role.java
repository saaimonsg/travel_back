package com.example.travelling.bundle.appuser.domain;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.infra.core.domain.AbstractPersistableCustom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "app_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" },
        name = "unq_name") })
@Getter
@Setter
@RequiredArgsConstructor
public class Role extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;


    public Role(String name, String description, Boolean disabled) {
        this.name = name;
        this.description = description;
        this.disabled = disabled;
    }
}

