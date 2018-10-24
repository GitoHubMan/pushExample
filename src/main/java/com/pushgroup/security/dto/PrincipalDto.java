package com.pushgroup.security.dto;

import java.util.List;

public class PrincipalDto {
    private String login;
    private List<String> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
