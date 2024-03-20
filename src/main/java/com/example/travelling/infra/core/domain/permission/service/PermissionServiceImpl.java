package com.example.travelling.infra.core.domain.permission.service;

import com.example.travelling.infra.core.domain.permission.Permission;
import com.example.travelling.infra.core.domain.permission.PermissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionJpaRepository repository;
    @Override
    public List<Permission> findAll() {
        return repository.findAll();
    }

    @Override
    public Permission save(Permission permission) throws Exception {
        if(permission.getResourceName().isEmpty() || permission.getResourceName().isBlank() || permission.getResourceName() == ""){
            throw new Exception("permission.resourcename.empty");
        }
        return repository.save(permission);
    }

    @Override
    public Permission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Permission update(Permission permission, Long permissionId) {
        Permission data = repository.findById(permissionId).orElseThrow();
        if(!permission.getResourceName().equals(data.getResourceName())){
            data.setResourceName(permission.getResourceName());
        }
        return repository.saveAndFlush(data);
    }

    @Override
    public void delete(Long permissionId) {
        Permission permission = repository.findById(permissionId).orElseThrow();
        repository.delete(permission);
    }


}
