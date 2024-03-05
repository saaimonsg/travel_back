package com.example.travelling.infra.security.service;

import com.example.travelling.appuser.AppUserRepository;
import com.example.travelling.appuser.AppUser;
import com.example.travelling.infra.security.data.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new CustomUserDetails(user.getUsername(), user.getPassword(), authorities);
    }
}
