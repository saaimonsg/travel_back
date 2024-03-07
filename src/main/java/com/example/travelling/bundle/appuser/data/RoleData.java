package com.example.travelling.bundle.appuser.data;

import lombok.Data;

import java.util.Collection;

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



}
