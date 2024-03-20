package com.example.travelling.infra.core.domain.permission.service;

import com.example.travelling.infra.core.domain.permission.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    Permission save(Permission permission) throws Exception;

    Permission findById(Long id);

    Permission update(Permission permission, Long permissionId);

    void delete(Long permissionId);
}
