package com.example.travelling.infra.core.domain.appuser.service;

import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import com.example.travelling.infra.core.domain.role.Role;
import com.example.travelling.infra.core.domain.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserJpaRepository appUserJpaRepository;
    private final RoleService roleService;

    @Override
    public void registerUser(AppUserData appUser) throws AppUserExceptionError {

        String username = appUser.getUsername().toLowerCase();

        AppUser userByUsername = appUserJpaRepository.findByUsername(username);
        String validateUsername = userByUsername.getUsername();
        String validateEmail = userByUsername.getEmail();
        if (validateUsername != null && validateEmail != null && !userByUsername.isNew()) {
            throw new AppUserExceptionError("user.exists");
        }

//        List<Role> roleList = new ArrayList<>();
//        roleList.add(roleService.findByName("ROLE_USER"));


        if (appUser.getEmail().isBlank() || appUser.getEmail().isEmpty() || appUser.getName().isBlank() ||
                appUser.getName().isEmpty() || appUser.getSurname().isBlank() || appUser.getSurname().isEmpty()) {
            throw new AppUserExceptionError("user.not.valid");
        }
        AppUser temp = appUserJpaRepository.findByUsername(appUser.getUsername().toLowerCase());

        temp.setNew(false);
        temp.setName(appUser.getName());
        temp.setSurname(appUser.getSurname());
        temp.setEmail(appUser.getEmail().toLowerCase());


        appUserJpaRepository.saveAndFlush(temp);


        //TODO send validateEmail

    }

    @Override
    public AppUserData fetchOne(Long id) throws AppUserExceptionError {
        Optional<AppUser> userOptional = appUserJpaRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new AppUserExceptionError("user.not.exists");
        }
        return new AppUserData(userOptional.get());
    }

    @Override
    public AppUserData findByUsername(String username) {
        AppUser appUser = appUserJpaRepository.findByUsername(username.toLowerCase());
        AppUserData userData = new AppUserData(appUser);
        return userData;
    }

    @Override
    public List<AppUserData> findAll() {
        List<AppUser> all = appUserJpaRepository.findAll();
        List<AppUserData> userDataList = new ArrayList<>();
        all.forEach(appUser -> userDataList.add(new AppUserData(appUser)));
        return userDataList;
    }

    @Override
    public AppUserData update(AppUserData appUserData) {
        AppUser appUser = appUserJpaRepository.findByUsername(appUserData.getUsername().toLowerCase());
        appUser.setPassword(appUser.getPassword());
        appUser.setName(appUserData.getName());
        appUser.setSurname(appUserData.getSurname());
        appUser.setEmail(appUserData.getEmail());
        appUser.setDriver(appUserData.isDriver());
        appUserJpaRepository.save(appUser);
        return appUserData;
    }
}
