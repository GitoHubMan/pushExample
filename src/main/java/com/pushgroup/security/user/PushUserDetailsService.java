package com.pushgroup.security.user;

import com.pushgroup.core.mapper.AuthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import java.util.List;



public class PushUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PushUserDetailsService.class);

    @Autowired
    protected AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername started for '{}'", username);
        PushUserDetails userDetails = authMapper.loadUserByUsername(username);
        if(userDetails == null) {
            LOGGER.error("Can't find user for username = {}", username);
            throw new UsernameNotFoundException("Can't find user for username = " +username);
        }
        List<SimpleGrantedAuthority> auths = loadAuthoritiesByUsername(username);
        if(CollectionUtils.isEmpty(auths)) {
            LOGGER.error("Can't find any role for username = {}", username);
            throw new UsernameNotFoundException("Can't find any role for username = " +username);
        }
        return createUserDetails(userDetails, auths);
    }

    private List<SimpleGrantedAuthority> loadAuthoritiesByUsername(String username) {
        return authMapper.loadAuthoritiesByUsername(username);
    }

    private PushUserDetails createUserDetails(PushUserDetails userDetails, List<SimpleGrantedAuthority> authorities) {
        LOGGER.info("createUserDetails started for '{}'", userDetails.getUsername());
        return new PushUserDetails(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(), authorities);
    }

}
