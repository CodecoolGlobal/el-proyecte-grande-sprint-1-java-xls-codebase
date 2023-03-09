package com.codecool.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@RestController
public class GatewayController {

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse httpServletResponse, Principal principal) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        httpServletResponse.sendRedirect("http://127.0.0.1:3000/success/" + principal.getName());
    }

    @GetMapping("oidc-principal")
    public OidcUser getOidcUserPrincipal(@AuthenticationPrincipal OidcUser principal) {
        return principal;
    }
}
