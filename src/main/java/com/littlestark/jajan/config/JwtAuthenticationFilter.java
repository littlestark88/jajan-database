package com.littlestark.jajan.config;

import com.littlestark.jajan.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("validationToken 1");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("validationToken 2");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwt = authHeader.substring(7);
            email = jwtService.extractUsername(jwt);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
                if(jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }
//        jwt = authHeader.substring(7);
//        email = jwtService.extractUsername(jwt);
//        log.info("validationEmail" + email);
//        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            log.info("validationToken 3");
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//            if(jwtService.isTokenValid(jwt, userDetails)) {
//                log.info("validationToken 4");
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                log.info("validation 4" + authToken.getDetails());
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            } else {
//                log.info("validationToken 5");
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expired");
//            }
//        } else {
//            log.info("validationToken 6");
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expired");
//        }
//        log.info("validationToken 7");
//        filterChain.doFilter(request, response);
    }
}
