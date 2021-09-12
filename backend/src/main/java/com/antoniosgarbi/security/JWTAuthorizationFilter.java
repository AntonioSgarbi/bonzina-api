package com.antoniosgarbi.security;

import com.antoniosgarbi.service.UserDetailsServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.antoniosgarbi.security.SecurityEnvironment.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private SecurityEnvironment securityEnvironment;
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsServiceImpl userDetailsService, SecurityEnvironment securityEnvironment) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.securityEnvironment = securityEnvironment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(securityEnvironment.getHEADER_ATTRIBUTE());

        if (attribute == null || !attribute.startsWith(securityEnvironment.getATTRIBUTE_PREFIX())) {
            chain.doFilter(request, response);
            return;
        }
        String token = attribute.replace(securityEnvironment.getATTRIBUTE_PREFIX(), "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

        String login = JWT.require(Algorithm.HMAC512(securityEnvironment.getTokenkey()))
                .build()
                .verify(token)
                .getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }
}
