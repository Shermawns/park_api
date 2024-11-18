package com.shermann.park_api.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer: ";
    public static final String JWT_AUTHORIZATION = "Authorization: ";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 2;

    public JwtUtils() {
    }

    private static Key generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date expireDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static JwtToken createToken(String username, String role){
        Date issueAt = new Date();
        Date limit = expireDate(issueAt);

        String token = Jwts.builder().
                setHeaderParam("typ", "JWT").
                setSubject(username)
                .setIssuedAt(issueAt)
                .setExpiration(limit)
                .signWith(generateKey(), SignatureAlgorithm.ES256)
                .claim("role", role)
                .compact();

        return new JwtToken(token);
    }

    public static Claims getClaimsFromToken(String token){
        try {
            return Jwts.parser().setSigningKey(generateKey()).build()
                    .parseClaimsJws(refactorToken(token)).getBody();
        }catch (JwtException exception){
            log.error(String.format("Invalid Token %s", exception.getMessage()));
        }

        return null;
    }


    public static String getUsernameByToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(generateKey()).build()
                    .parseClaimsJws(refactorToken(token));

            return true;

        }catch (JwtException exception){
            log.error(String.format("Invalid Token %s", exception.getMessage()));
        }

        return false;
    }

    public static String refactorToken(String token){
        if (token.contains(JWT_BEARER)){
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
