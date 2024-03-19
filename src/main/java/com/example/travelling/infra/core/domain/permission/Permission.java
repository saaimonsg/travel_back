package com.example.travelling.infra.core.domain.permission;

import com.example.travelling.infra.core.jpa.AbstractPersistableCustom;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "auth_permission")
public class Permission extends AbstractPersistableCustom {

    private String resourceName;

    public Permission(String locationResourceName) {
        this.resourceName = locationResourceName;
    }

    public Permission() {

    }
}
