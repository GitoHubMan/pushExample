package com.pushgroup.security.auth;

import com.pushgroup.core.util.Helper;
import com.pushgroup.security.user.PushUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class FormAuthenticationProvider extends DaoAuthenticationProvider {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(Helper.BCRYPT_SALT_LENGTH);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof FormAuthenticationToken) {
            PushUserDetails userDetails = (PushUserDetails)getUserDetailsService().loadUserByUsername(authentication.getCredentials().toString());
            if(userDetails != null && passwordEncoder.matches(((PushUserDetails)authentication.getPrincipal()).getPassword(), userDetails.getPassword())) {
                return new FormAuthenticationToken(userDetails);
            }
        }
        throw new BadCredentialsException(this.messages.getMessage("FormAuthenticationProvider.authenticate.badCredentials", "Bad credentials"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FormAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
