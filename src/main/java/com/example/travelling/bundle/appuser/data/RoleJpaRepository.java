package com.example.travelling.bundle.appuser.data;

import com.example.travelling.bundle.appuser.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleName);
}
