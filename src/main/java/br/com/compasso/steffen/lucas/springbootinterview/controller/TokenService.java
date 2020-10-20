package br.com.compasso.steffen.lucas.springbootinterview.controller;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.compasso.steffen.lucas.springbootinterview.dto.TokenDto;
import br.com.compasso.steffen.lucas.springbootinterview.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {
    
    @Value("${springbootinterview.jwt.expiration}")
    private String expiration;

    @Value("${springbootinterview.jwt.secret}")
    private String secret;

    public TokenDto generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Long.parseLong(this.expiration));

        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
            .setIssuer("br.com.compasso.steffen.lucas.springbootinterview")
            .setSubject(user.getId().toString())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key)
            .compact();
        
        return new TokenDto(token, "Bearer", Long.parseLong(this.expiration));
    }

    public boolean isTokenValid(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        try {
            Jwts.parserBuilder().setSigningKey(keyBytes).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public Long getUserId(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
		return Long.parseLong(Jwts.parserBuilder().setSigningKey(keyBytes).build().parseClaimsJws(token).getBody().getSubject());
	}
}
