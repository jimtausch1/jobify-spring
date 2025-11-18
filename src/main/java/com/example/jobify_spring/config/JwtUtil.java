package com.example.jobify_spring.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {
  @Value("${jwtKey}")
  private String secret;

  public Boolean validateTokenByUser(String username, UserDetails userDetails) {
    return username.equals(userDetails.getUsername());
  }

  public String getUserNameFromToken(String token) {
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
