package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.service.interfaces.IJWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/*
 * 1. In order to send Bearer Auth to frontend, we first need to generate a token, when a user logs in - handled by generateToken function
 * 2. Next time the logged-in user uses an API, we will not require them to log in, but pass the generated token as a Bearer Auth.
 * 3. When the backend, received the Bearer auth, we need to validate it:
 *    In SecurityConfig, we added a rule "addFilterBefore", where JwtFiler class in config package, uses the JWTService validation token functions.
 */
@Service
public class JWTService implements IJWTService {

    private static final int SESSION_LENGTH_MINS = 60;
    private static final String CRYPTO_ALGO = "HmacSHA256";

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + convertMinsToMilliSecs(SESSION_LENGTH_MINS)))
                .and()
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token).toLowerCase();
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public long getTokenExpiry(String token) {
        return extractExpiration(token).getTime();
    }

    // Helpers:
    private int convertMinsToMilliSecs(int mins) {
        return mins * 60 * 1000;
    }

    private SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        // extracts all claims from token
        final Claims claims =
                Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        return claimResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
