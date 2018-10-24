package com.pushgroup.security.auth;

import com.pushgroup.core.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class FormAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormAuthenticationProcessingFilter.class);


    protected FormAuthenticationProcessingFilter() {
        super("**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter(Helper.REQUEST_USERNAME_ATTR_NAME);
        String password = httpServletRequest.getParameter(Helper.REQUEST_PASSWORD_ATTR_NAME);
        GrantedAuthority anonymousRole = new SimpleGrantedAuthority("ANONYMOUS");

        if(username == null || password == null) {
            Authentication currAuth = SecurityContextHolder.getContext().getAuthentication();
            if(currAuth == null) {
                Authentication anonAuth = new AnonymousAuthenticationToken(Helper.ANONYMOUS_NAME, Helper.ANONYMOUS_NAME, Arrays.asList(anonymousRole));
                SecurityContextHolder.getContext().setAuthentication(anonAuth);
            }
            return SecurityContextHolder.getContext().getAuthentication();
        }
        return getAuthenticationManager().authenticate(new FormAuthenticationToken(username, password, Arrays.asList(anonymousRole)));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
