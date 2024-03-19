package com.example.travelling.infra.core.domain.role;

import com.example.travelling.infra.core.domain.permission.Permission;
import lombok.Data;

import java.util.List;

@Data
public class RoleData {
    private  Long id;
    private  final String name;
    private  final String description;
    private  final Boolean disabled;
    private  final List<Permission> permissionList;
    public RoleData(Long id, String name, String description, Boolean disabled, List<Permission> permissionList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.disabled = disabled;
        this.permissionList = permissionList;
    }


    public Role toRole() {
        return new Role(this.name,this.description,this.disabled);
    }
}
