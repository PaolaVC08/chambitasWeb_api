package com.ann.chambitasWeb.security.jwt;

import com.ann.chambitasWeb.security.services.UsuarioDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${chambitas.app.jwtSecret}")
  private String jwtSecret;

  @Value("${chambitas.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  // Método para crear clave criptográfica
  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // Método para generar JWT
  public String generateJwtToken(Authentication authentication) {
    UsuarioDetailsImpl usuarioPrincipal = (UsuarioDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(usuarioPrincipal.getUsername())  // getUsername() retorna el correo
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // Método para obtener el correo del token
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  // Método para validar token
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parse(authToken);
      return true;
    } catch (SecurityException | MalformedJwtException e) {
      logger.error("Token JWT inválido: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("Token JWT expirado: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("Token JWT no soportado: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("Claims JWT vacíos: {}", e.getMessage());
    }

    return false;
  }
}

