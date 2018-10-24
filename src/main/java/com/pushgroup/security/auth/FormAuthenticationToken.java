package com.pushgroup.security.auth;

import com.pushgroup.security.user.PushUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class FormAuthenticationToken extends AbstractAuthenticationToken {
    private String username;
    private String password;
    private UserDetails userDetails;


    public FormAuthenticationToken(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        this.userDetails = new PushUserDetails(username, password, true, authorities);
        super.setAuthenticated(false);
    }

    public FormAuthenticationToken(PushUserDetails pushUserDetails) {
        super(pushUserDetails.getAuthorities());
        this.eraseCredentials();
        this.userDetails = pushUserDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if(authenticated)
            throw new IllegalArgumentException("Cannot set the token to trusted. Use the constructor with a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.username = null;
    }
}
