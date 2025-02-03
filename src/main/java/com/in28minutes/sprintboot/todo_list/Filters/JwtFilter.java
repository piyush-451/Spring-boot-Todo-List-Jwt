package com.in28minutes.sprintboot.todo_list.Filters;

import com.in28minutes.sprintboot.todo_list.Service.JwtService;
import com.in28minutes.sprintboot.todo_list.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String requestURI = request.getRequestURI();
            if (requestURI.equals("/login") || requestURI.equals("/signup")) {
                filterChain.doFilter(request, response); // Skip the filter for these endpoints
                return;
            }

            String authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
//                throw new ServletException("No Authorization token");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Authorization token or invalid token format");
//                filterChain.doFilter(request, response);
                return;  // Do not continue processing, send the error response immediately
            }


            String token = authorizationHeader.substring(7);

            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                if(jwtService.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);

        }
        catch (Exception e){
//            filterChain.doFilter(request, response);
            System.out.println(e.getMessage());
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Authorization token or invalid token format");

        }
    }

}
