package com.example.accounts.securites;

import com.example.accounts.Service.ServiceImp.CustomUserDetail;
import com.example.accounts.Service.ServiceImp.ImpCustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    final private ImpCustomUserDetailsService impCustomUserDetailsService;
    final private JwtService jwtService;
    public JwtAuthenticationFilter(ImpCustomUserDetailsService impCustomUserDetailsService, JwtService jwtService) {
        this.impCustomUserDetailsService = impCustomUserDetailsService;
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
   filterChain.doFilter(request, response);
        return;
    }
    String token = authorizationHeader.substring(7);
    String username = jwtService.extractUsername(token);
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        CustomUserDetail userDetail= (CustomUserDetail) impCustomUserDetailsService.loadUserByUsername(username);
        if(jwtService.validateToken(token,userDetail.getUsername())){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
    }
        doFilter(request,response,filterChain);

    }
}
