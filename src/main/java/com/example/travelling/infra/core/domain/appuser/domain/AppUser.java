package com.example.travelling.infra.core.domain.appuser.domain;


import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.infra.core.domain.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class AppUser  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String surname;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private boolean isDriver = false;

    @Column(nullable = false)
    private boolean isNew = true;

    @Builder.Default
    @Column(nullable = true)
    private Long likes = 0L;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Country country;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Province province;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private City city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_app_user_auth_role",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_app_user_friends",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @Builder.Default
    private Collection<AppUser> friends = new ArrayList<>();




    public AppUser(String name, String surname, String email, String username, String password,
                   Collection<Role> role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = role;
    }

    public AppUser(String name, String surname, String email, String username, String password,
                   Collection<Role> role,Country country, Province province, City city) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = role;
        this.country = country;
        this.province = province;
        this.city = city;
    }
    public AppUser(String name, String surname, String email, String username, String password,
                   Collection<Role> role,Country country, Province province, City city,
                   Collection<AppUser> friends) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = role;
        this.country = country;
        this.province = province;
        this.city = city;
        this.friends = friends;
    }

    public AppUser(Long id, String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
