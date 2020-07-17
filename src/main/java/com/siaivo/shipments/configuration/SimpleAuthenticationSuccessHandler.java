package com.siaivo.shipments.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if(authority.getAuthority().equalsIgnoreCase("ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin/allUsers");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(authority.getAuthority().equalsIgnoreCase("sales")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/sales/inWorkBidsList");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(authority.getAuthority().equalsIgnoreCase("purchase")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/purchase/purchaseBidsList");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if(authority.getAuthority().equalsIgnoreCase("logist")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/logist/logistBidsList");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                throw new IllegalStateException();
            }
        });

    }

}
