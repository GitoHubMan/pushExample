package com.pushgroup.core.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class RouteRest {

    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/landing")
    public String getLandingPage(@RequestParam(value = "clickId", required = false)String clickId) {
        return "landing";
    }
}
