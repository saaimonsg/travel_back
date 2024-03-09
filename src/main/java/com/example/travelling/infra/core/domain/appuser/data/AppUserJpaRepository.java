package com.example.travelling.infra.core.domain.appuser.data;


import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUser,Long> {
    @Query("Select appUser from AppUser appUser where appUser.username = :username")
    AppUser findAppUserByName(@Param("username") String username);

    AppUser findByUsername(String username);
}
