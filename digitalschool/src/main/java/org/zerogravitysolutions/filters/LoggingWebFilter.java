package org.zerogravitysolutions.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingWebFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingWebFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("Incoming request {} {}", request.getMethod(), request.getRequestURI());

        // Continue processing the request
        filterChain.doFilter(request, response);

        int statusCode = response.getStatus();
        logger.info("Outgoing response {} for {} {}", statusCode, request.getMethod(), request.getRequestURI());

        if(statusCode >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
