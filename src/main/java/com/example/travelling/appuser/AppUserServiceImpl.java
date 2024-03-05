package com.example.travelling.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUserData save(AppUserData appUser) {
        String password = new BCryptPasswordEncoder().encode(appUser.getPassword());
        AppUser user = new AppUser(appUser.getName(), appUser.getSurname(), appUser.getEmail(), appUser.getUsername(), password, appUser.getRole());
        appUserRepository.save(user);
        return appUser;
    }

    @Override
    public AppUserData fetchOne(Long id) throws AppUserExceptionError {
        Optional<AppUser> byId = appUserRepository.findById(id);
        if (!byId.isPresent()) {
            throw new AppUserExceptionError("user.not.exists");
        }

        AppUser user = byId.get();
        return new AppUserData(user);
    }

    public List<AppUser> fetchAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUserData getLoggedInUser(String username) {
        return new AppUserData(appUserRepository.findByUsername(username));
    }
}
