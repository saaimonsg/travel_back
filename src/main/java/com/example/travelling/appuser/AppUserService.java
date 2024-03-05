package com.example.travelling.appuser;


import java.util.List;

public interface AppUserService {
    AppUserData save(AppUserData appUser);
    AppUserData fetchOne(Long id) throws AppUserExceptionError;


    List<AppUser> fetchAll();


    AppUserData getLoggedInUser(String username);
}
