package com.example.travelling.infra.core.domain.appuser.service;


import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;

import java.util.List;

public interface AppUserService {
    AppUserData save(AppUserData appUser) throws AppUserExceptionError;
    AppUserData fetchOne(Long id) throws AppUserExceptionError;
    AppUser getByUsername(String username) throws AppUserExceptionError;


    List<AppUser> fetchAll();


    AppUserData getLoggedInUser(String username);


}
