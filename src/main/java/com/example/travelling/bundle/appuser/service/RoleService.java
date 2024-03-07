package com.example.travelling.bundle.appuser.service;


import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.domain.Role;

import java.util.List;

public interface RoleService {
    Role create(RoleData roleData);
    List<Role> findAll();
    Role findByName(String roleName);

}
