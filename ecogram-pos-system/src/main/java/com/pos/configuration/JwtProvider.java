package com.pos.configuration;




import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    public SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication authentication){
        Collection <?extends GrantedAuthority >authorities = authentication.getAuthorities();
        String roles = populateAuthorities(authorities);
        
        return Jwts.builder()
        .issuedAt(new Date(new Date().getTime()+ 86400000))
        .claim("email",authentication.getName())
        .claim("authorities",roles)
        .signWith(key)
        .compact();
        }

    
}
