package com.technicaltest.user.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private static final String SECRET_KEY="61525e6e303f7075492f6f7e41344b285b78406a3a352346283037387e";

    public String getToken(String user) {
        return getToken(new HashMap<>(), user);
    }

    public String getToken(Map<String,Object> extraClaims, String user) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
       byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    
}