package com.example.travelling.infra.security.filter;

import com.example.travelling.infra.security.data.UserAuthenticatedData;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;

/**
 * To implement a custom basic authentication filter in your CustomFilter.java class,
 * you need to override the doFilterInternal method of the BasicAuthenticationFilter class.
 * This method is called for every request that passes through the filter chain.
 * Here, you can add your custom authentication logic.
 *
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
 *
 * Here's how you can implement this in your CustomFilter.java class:
 */
@Service
public class CustomFilter extends BasicAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomFilter.class);


    private final String tenantRequestHeader = "Travelling-Id";
    private static boolean firstRequestProcessed = false;
    private final boolean exceptionIfHeaderMissing = true;
    private final Gson gson ;

    @Autowired
    public CustomFilter(final AuthenticationManager authenticationManager,
                        final AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
        gson = new Gson();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
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
                Authentication authResult = super.getAuthenticationManager().authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }

        } catch (AuthenticationException ex) {
            //FIXME TRAV-1
            LOG.error("Authentication failed", ex);
            response.sendError(HttpStatus.UNAUTHORIZED.value(),
                    gson.toJson(new UserAuthenticatedData(null,null)) );
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
