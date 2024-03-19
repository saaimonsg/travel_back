package com.example.travelling.infra.core.domain.appuser.service;


import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;

import java.util.List;

public interface AppUserService {
    void registerUser(AppUserData appUser) throws AppUserExceptionError;
    AppUserData fetchOne(Long id) throws AppUserExceptionError;
    AppUserData findByUsername(String username) throws AppUserExceptionError;
    List<AppUserData> findAll();
    AppUserData update(AppUserData appUserData);


}
