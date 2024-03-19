package com.example.travelling.infra.security.service;

import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.security.data.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service(value = "customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserJpaRepository repository;

    @Autowired
    public CustomUserDetailsService(AppUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.repository.findAppUserByName(username.toLowerCase());

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        });


        return new CustomUserDetails(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
