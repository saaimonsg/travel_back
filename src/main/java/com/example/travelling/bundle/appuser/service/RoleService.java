package com.example.travelling.bundle.appuser.service;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.domain.Role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> findAll();

    RoleData save(RoleData roleData);

    Role findById(Long roleId);

    RoleData edit(RoleData roleData);
}
