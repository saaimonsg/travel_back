package com.example.travelling.infra.core.domain.permission;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepository extends JpaRepository<Permission, Long>
{
}
