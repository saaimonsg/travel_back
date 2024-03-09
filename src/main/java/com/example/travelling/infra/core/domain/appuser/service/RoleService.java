package com.example.travelling.infra.core.domain.appuser.service;

import com.example.travelling.infra.core.domain.appuser.data.RoleData;
import com.example.travelling.infra.core.domain.appuser.domain.Role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> findAll();

    RoleData save(RoleData roleData);

    Role findById(Long roleId);

    RoleData edit(RoleData roleData);
}
