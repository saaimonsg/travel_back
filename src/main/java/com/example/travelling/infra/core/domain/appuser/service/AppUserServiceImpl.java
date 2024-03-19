package com.example.travelling.infra.core.domain.appuser.service;

import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserJpaRepository appUserJpaRepository;


    @Override
    public void registerUser(AppUserData appUser) throws AppUserExceptionError {
        AppUser userByUsername = null;
        String username = appUser.getUsername().toLowerCase();

        userByUsername = appUserJpaRepository.findByUsername(username);
        Long id = userByUsername.getId();
        String name = userByUsername.getName();
        String surname = userByUsername.getSurname();
        String email = userByUsername.getEmail();
        if (name != null && surname != null && email != null) {
            throw new AppUserExceptionError("user.exists");
        } else {
            userByUsername.setId(id);
            userByUsername.setName(appUser.getName());
            userByUsername.setSurname(appUser.getSurname());
            userByUsername.setEmail(appUser.getEmail());
            appUserJpaRepository.saveAndFlush(userByUsername);
        }


        //TODO send email

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
