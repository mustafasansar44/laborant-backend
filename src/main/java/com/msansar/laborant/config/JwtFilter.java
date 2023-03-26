package com.msansar.laborant.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.msansar.laborant.service.UserDetailsServiceImpl;
import com.msansar.laborant.utils.TokenGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenGenerator tokenGenerator;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ObjectMapper objectMapper;

    public JwtFilter(TokenGenerator tokenGenerator, UserDetailsServiceImpl userDetailsServiceImpl, ObjectMapper objectMapper) {
        this.tokenGenerator = tokenGenerator;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        String username;
        try {
            if(!token.isBlank()){
                username = tokenGenerator.validateToken(token).getSubject();
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }catch (Exception exception){
            response.setContentType("application/json");
            Map<String, String> errors = new HashMap<>();
            errors.put("Error: ", exception.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(errors));
        }
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || !header.startsWith("Bearer ")){
            return "";
        }
        return header.substring(7);
    }
}
