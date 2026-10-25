package com.example.accounts.securites;

import com.example.accounts.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static tools.jackson.databind.type.LogicalType.DateTime;
@Component
public class JwtService {
    private static String SECRET = "sljflsjflkajflksjjjjjjjsj";

    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    private static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public static String extractUsername(String token) {
        return parseToken(token).getSubject();

    }
    public static Date extractExpiration(String token) {
        return parseToken(token).getExpiration();
    }
    public static boolean validateToken(String token, String username) {
        return parseToken(token).getSubject().equals(username) && extractExpiration(token).before(new Date());
    }


}
