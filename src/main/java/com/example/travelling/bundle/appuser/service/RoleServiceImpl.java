package com.example.travelling.bundle.appuser.service;

import com.example.travelling.bundle.appuser.data.RoleData;
import com.example.travelling.bundle.appuser.data.RoleJpaRepository;
import com.example.travelling.bundle.appuser.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleJpaRepository repository;
    @Override
    public Role create(RoleData roleData) {
        Role role = new RoleData().toRole(roleData);
        return repository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findByName(String roleName) {
        return repository.findByName(roleName);
    }
}
