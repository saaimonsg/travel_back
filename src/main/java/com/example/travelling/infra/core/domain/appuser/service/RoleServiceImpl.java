package com.example.travelling.infra.core.domain.appuser.service;

import com.example.travelling.infra.core.domain.appuser.data.RoleData;
import com.example.travelling.infra.core.domain.appuser.data.RoleJpaRepository;
import com.example.travelling.infra.core.domain.appuser.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleJpaRepository roleJpaRepository;

    @Autowired
    public RoleServiceImpl(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Collection<Role> findAll() {
        return roleJpaRepository.findAll();
    }

    @Override
    public RoleData save(RoleData roleData) {
        Role role = roleData.toRole();
        roleJpaRepository.save(role);
        return roleData;
    }

    @Override
    public Role findById(Long roleId) {
        return roleJpaRepository.findById(roleId).orElseThrow();
    }

    @Override
    public RoleData edit(RoleData roleData) {
        Role role = findById(roleData.getId());
        role.setName(roleData.getName());
        role.setDescription(roleData.getDescription());
        role.setDisabled(roleData.getDisabled());
        roleJpaRepository.save(role);
        return roleData;
    }
}
