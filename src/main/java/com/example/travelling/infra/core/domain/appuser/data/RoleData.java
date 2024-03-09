package com.example.travelling.infra.core.domain.appuser.data;

import com.example.travelling.infra.core.domain.appuser.domain.Role;
import lombok.Data;

@Data
public class RoleData {
    private  Long id;
    private  String name;
    private  String description;
    private  Boolean disabled;

    public RoleData(Long id, String name, String description, Boolean disabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.disabled = disabled;
    }


    public Role toRole() {
        return new Role(this.name,this.description,this.disabled);
    }
}
