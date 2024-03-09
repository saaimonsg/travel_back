package com.example.travelling.infra.core.domain.appuser.domain;

import com.example.travelling.infra.core.domain.AbstractPersistableCustom;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
}
