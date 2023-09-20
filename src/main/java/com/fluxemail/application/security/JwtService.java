package com.fluxemail.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

//    private static final String SECRET_KEY="26a89f6ada326ee1b8810b0a640675a1d74395e7dac5dcc245e9115317b13675";
    private static final String SECRET_KEY="E+kTXjg1B45+8JCP4mzxPCLcMQfuA4tqYCBpQbF1IsymPg/6a7Fs/9MJ9ScAxWQM9ycDtUvn/4ypfPPnR5il4xV56RD59D88aLOS5Ns9/dFMCQGD0CBhBbdvSIQ/T/BWTyF2I6SPaj8CVmp1XnhvClLQTgP09lWywcza9sQ4VMZBowlWybgc+j6yMKdRj+PAJ3okJhbmpZFI/CxQXzUd1rKckIwGYfKV0sfpHL4SHSAR9uyBPKA8Z7nX8dXYmeisckxrk4u2QHRYqLO0RrYqEkQ6hJ/tKDMK54LPdlHXVbZ9ymjX5VY84/7H2O+m2Z6iyYnREEPtsUm3wLq9kAZaFXtx7SB+EKn5s6JEuLykiM7sjSb018M0b6WpxTQrA0CVEO2krenNDR7jeC75oWhoN4XywSaFZAl9GId9HGL7c4UMeBswEZh85ZVfPf/TZntYqtvi1VUXZmUWPDo9zwflNfE44HWKfF5ybe2mwTH6t1zwJvcDiGVkFYA+oFmjReBFHOIfzoZXNbGw2aguyJqnHsu/tQ9Gu6AQzPMUczYiSn/KFRY8TaXf09Irx+19edshr6T+2MQHqNtJul26eb8kCSM2WLQWSIykO72rOZdTegViW4Dw1fpxuOcdUvVT4sq5mxiWeWL7qCg2jJpcIqeNQrG1JgmyEhb1k1ihzjLEroyouS0Fq+4jmA4E27vSiJJg\n";
    public String extractUserEmail(String token) {

        return extractClaim(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(Map<String, Object> extraClaims , UserDetails userDetails){
        return  Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 ))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
        // .signWith(getSigningKey(), SignatureAlgorithm.HS256)

    }

    public String generateToken(UserDetails userDetails){
        return generateToken( new HashMap<>() , userDetails);
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName = extractUserEmail(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
