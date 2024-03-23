package com.example.travelling.infra.core.domain.role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> findAll();

    RoleData save(RoleData roleData);

    Role findById(Long roleId);

    RoleData edit(RoleData roleData);

    Role delete(Long roleId);

    Role findByName(String roleUser);
}
