package com.pushgroup.security.rest;


import com.pushgroup.security.dto.PrincipalDto;
import com.pushgroup.security.user.PushUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class AuthRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRest.class);

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(){
        LOGGER.info("POST to /auth/login");
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(){
        LOGGER.info("POST to /auth/logout");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody PrincipalDto currentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDto principalDto = new PrincipalDto();
        principalDto.setLogin(auth.getName());
        principalDto.setRoles(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return principalDto;
    }
}
