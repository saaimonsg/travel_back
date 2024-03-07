package com.example.travelling.infra.security.service;

import com.example.travelling.bundle.appuser.data.AppUserJpaRepository;
import com.example.travelling.bundle.appuser.domain.AppUser;
import com.example.travelling.infra.security.data.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserJpaRepository repository;

    @Autowired
    public CustomUserDetailsService(AppUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = this.repository.findAppUserByName(username);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));

        return new CustomUserDetails(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
