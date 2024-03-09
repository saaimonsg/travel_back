package com.example.travelling.infra.core.domain.appuser.data;

import com.example.travelling.infra.core.domain.appuser.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role,Long> {
    Role findByName(String visitor);
}
