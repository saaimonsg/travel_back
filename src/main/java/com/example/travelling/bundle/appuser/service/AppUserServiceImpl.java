package com.example.travelling.bundle.appuser.service;

import com.example.travelling.bundle.appuser.data.AppUserData;
import com.example.travelling.bundle.appuser.data.AppUserJpaRepository;
import com.example.travelling.bundle.appuser.data.RoleJpaRepository;
import com.example.travelling.bundle.appuser.domain.Role;
import com.example.travelling.bundle.appuser.exception.AppUserExceptionError;
import com.example.travelling.bundle.appuser.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserJpaRepository appUserJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    @Autowired
    public AppUserServiceImpl(AppUserJpaRepository appUserJpaRepository, RoleJpaRepository roleJpaRepository) {
        this.appUserJpaRepository = appUserJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public AppUserData save(AppUserData appUser) throws AppUserExceptionError {
        AppUser userByUsername = null;
        String username = appUser.getUsername();

        userByUsername = appUserJpaRepository.findByUsername(username);
        if (userByUsername != null) {
            throw new AppUserExceptionError("user.already.exists");
        }

        String password = new BCryptPasswordEncoder().encode(appUser.getPassword());
        Role role = appUser.getRole();
        String name = appUser.getName();
        String surname = appUser.getSurname();
        String email = appUser.getEmail();

        AppUser user = new AppUser(name, surname, email,
                username, password, role);
        appUserJpaRepository.save(user);
        appUser.setPassword("");
        return appUser;
    }

    @Override
    public AppUserData fetchOne(Long id) throws AppUserExceptionError {
        Optional<AppUser> userOptional = appUserJpaRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new AppUserExceptionError("user.not.exists");
        }
        return new AppUserData(userOptional.get());
    }

    public List<AppUser> fetchAll() {
        return appUserJpaRepository.findAll();
    }

    @Override
    public AppUserData getLoggedInUser(String username) {
        return new AppUserData(appUserJpaRepository.findByUsername(username));
    }
}
