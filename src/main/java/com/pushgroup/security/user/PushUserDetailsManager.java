package com.pushgroup.security.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

public class PushUserDetailsManager extends PushUserDetailsService implements UserDetailsManager {

    @Override
    public void createUser(UserDetails userDetails) {
        throw new UnsupportedOperationException("createUser is not allowed");
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        throw new UnsupportedOperationException("updateUser is not allowed");
    }

    @Override
    public void deleteUser(String s) {
        throw new UnsupportedOperationException("deleteUser is not allowed");
    }

    @Override
    public void changePassword(String s, String s1) {
        throw new UnsupportedOperationException("changePassword is not allowed");
    }

    @Override
    public boolean userExists(String s) {
        UserDetails userDetails = loadUserByUsername(s);
        return userDetails != null;
    }
}
