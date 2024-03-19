package com.example.travelling.infra.security.config;

import com.example.travelling.infra.core.domain.role.RoleJpaRepository;
import com.example.travelling.infra.security.filter.CustomFilter;
import com.example.travelling.infra.security.filter.ResponseCorsFilter;
import com.example.travelling.infra.security.service.CustomUserDetailsService;
import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@Slf4j
public class SecurityConfig implements WebMvcConfigurer {

    private final AppUserJpaRepository appUserJpaRepository;
    private final CustomUserDetailsService userDetailsService;
    private final RoleJpaRepository roleJpaRepository;

    @Autowired
    public SecurityConfig(AppUserJpaRepository appUserJpaRepository, CustomUserDetailsService userDetailsService, RoleJpaRepository roleJpaRepository) {
        this.appUserJpaRepository = appUserJpaRepository;
        this.userDetailsService = userDetailsService;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(new CustomFilter(authenticationManager(), basicAuthenticationEntryPoint(),
                                appUserJpaRepository, passwordEncoder(), roleJpaRepository),
                        BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated()
                )
                .httpBasic(httpSecurityHttpBasicConfigurer -> {
                    httpSecurityHttpBasicConfigurer.authenticationEntryPoint(basicAuthenticationEntryPoint());
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(configurer -> {
                    configurer.configurationSource(ResponseCorsFilter.corsConfigurationSource);
                })
                .userDetailsService(userDetailsService)
                .authenticationProvider(authenticationProvider())
                .authenticationManager(authenticationManager())
                .build();
    }


    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("Travelling App");
        return basicAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(authenticationProvider());
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("::Authentication success:: \n" + authentication.getName());
            response.sendRedirect("/dashboard");
        };
    }

}
