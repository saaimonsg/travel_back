package com.example.travelling.infra.security.filter;

import com.example.travelling.infra.core.domain.appuser.data.AppUserJpaRepository;
import com.example.travelling.infra.core.domain.role.RoleJpaRepository;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.role.Role;
import com.example.travelling.infra.security.data.UserAuthenticatedData;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

/**
 * To implement a custom basic authentication filter in your CustomFilter.java class,
 * you need to override the doFilterInternal method of the BasicAuthenticationFilter class.
 * This method is called for every request that passes through the filter chain.
 * Here, you can add your custom authentication logic.
 * <p>
 * * Here's a step-by-step plan:
 * 1). Override the doFilterInternal method.
 * 2). Extract the Authorization header from the request.
 * 3). If the Authorization header is null or doesn't start with "Basic ", continue the filter chain
 * 4). without authenticating.
 * 5). If the Authorization header is present and starts with "Basic ", extract the base64 encoded
 * username and password.
 * 6). Decode the base64 encoded username and password.
 * 7). Create an UsernamePasswordAuthenticationToken with the decoded username and password.
 * 8). Authenticate the UsernamePasswordAuthenticationToken using the AuthenticationManager.
 * 9). If the authentication is successful, set the authentication in the SecurityContextHolder.
 * 10). Continue the filter chain.
 * <p>
 * Here's how you can implement this in your CustomFilter.java class:
 */
@Service
@Slf4j
public class CustomFilter extends BasicAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomFilter.class);
    private final Gson gson = new Gson();

    private final AppUserJpaRepository appUserJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleJpaRepository roleJpaRepository;


    public CustomFilter(final AuthenticationManager authenticationManager,
                        final AuthenticationEntryPoint authenticationEntryPoint,
                        AppUserJpaRepository appUserJpaRepository,
                        PasswordEncoder passwordEncoder,
                        RoleJpaRepository roleJpaRepository) {
        super(authenticationManager, authenticationEntryPoint);
        this.appUserJpaRepository = appUserJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (StringUtils.isEmpty(header) || !header.startsWith("Basic ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String[] tokens = extractAndDecodeHeader(header, request);
            assert tokens.length == 2;

            String username = tokens[0];
            String password = tokens[1];

            if (super.getAuthenticationManager() != null) {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                if (appUserJpaRepository.findByUsername(username) == null && request.getServletPath().equals("/authenticate/register")) {
                    AppUser appUser = new AppUser();
                    appUser.setUsername(username);
                    appUser.setPassword(passwordEncoder.encode(password));
                    Role roleUser = roleJpaRepository.findByName("ROLE_USER");
                    appUser.getRoles().add(roleUser);
                    appUserJpaRepository.save(appUser);
                    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    authRequest = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
                }
                log.debug("authRequest: {}", authRequest);
                log.debug("authRequest -> getPrincipal: {}", authRequest.getPrincipal());
                log.debug("authRequest -> getDetails: {}", authRequest.getDetails());
                log.debug("authRequest -> getAuthorities: {}", authRequest.getAuthorities());
                log.debug("authRequest -> isAuthenticated: {}", authRequest.isAuthenticated());
                log.debug("request-> getPathInfo: {}", request.getPathInfo());
                log.debug("request-> getRequestURI: {}", request.getRequestURI());
                log.debug("request-> getRequestURL: {}", request.getRequestURL());
                log.debug("request-> getServletPath: {}", request.getServletPath());
                Authentication authResult = super.getAuthenticationManager().authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }

        } catch (AuthenticationException ex) {
            //FIXME TRAV-1
            LOG.error("Authentication failed", ex);
            ex.printStackTrace();
            response.sendError(HttpStatus.UNAUTHORIZED.value(), gson.toJson(new UserAuthenticatedData(null, null)));
            return;
        } catch (IOException ex) {
            LOG.error("Failed to decode basic authentication token", ex);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Failed to decode basic authentication token");
            return;
        }
        chain.doFilter(request, response);
    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
