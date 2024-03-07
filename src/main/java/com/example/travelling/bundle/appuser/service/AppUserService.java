package com.example.travelling.bundle.appuser.service;


import com.example.travelling.bundle.appuser.data.AppUserData;
import com.example.travelling.bundle.appuser.exception.AppUserExceptionError;
import com.example.travelling.bundle.appuser.domain.AppUser;

import java.util.List;

public interface AppUserService {
    AppUserData save(AppUserData appUser) throws AppUserExceptionError;
    AppUserData fetchOne(Long id) throws AppUserExceptionError;


    List<AppUser> fetchAll();


    AppUserData getLoggedInUser(String username);
}
