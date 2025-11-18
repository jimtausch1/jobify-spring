package com.example.jobify_spring.config;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Value("${jwtKey}")
  private String secret;
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain chain) {
    final String authorizationHeader = request.getHeader("Authorization");

    String userName = null;
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      userName = getUserNameFromToken(jwt);
    }

    if (userName != null &&
        SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

      if (userDetails.isEnabled() && userDetails.isAccountNonExpired()) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    try {
      chain.doFilter(request, response);
    } catch (IOException | ServletException e) {
      e.printStackTrace();
    }
  }

  private String getUserNameFromToken(String token) {
    String userName = null;

    try {
      final Claims claims = Jwts.parserBuilder()
          .setSigningKey(secret.getBytes(Charset.forName("UTF-8")))
          .build()
          .parseClaimsJws(token)
          .getBody();
      userName = claims.get("name", String.class);
    } catch (ExpiredJwtException e) {
      System.out.println("JWT has expired.");
      // You can still access claims from an expired token if needed
      Claims expiredClaims = e.getClaims();
      System.out.println("Expired token subject: " + expiredClaims.getSubject());
    } catch (SignatureException e) {
      System.out.println("JWT signature is invalid.");
    } catch (Exception e) {
      System.out.println("Error parsing JWT: " + e.getMessage());
    }

    return userName;
  }
}
