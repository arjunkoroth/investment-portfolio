package com.hackathon.userservice.security;

import com.hackathon.userservice.exceptions.ApiKeySignatureException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {
	private static final String SECRET_KEY = "grandLibrary@";
	private static final String ISSUER = "com.hackathon.libraryservice";
	
	public String getApiKey(INGUser user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", user.getUsername());
		claims.put("userId", user.getUserId());
		return Jwts.builder()
				.setClaims(claims)
				.setIssuer(ISSUER)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(removeBearerFromToken(token))
                .getBody();
        return (String) claims.get("username");
    }
	
	public int getUserId(String token) {
		Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(removeBearerFromToken(token))
                .getBody();
        return (int) claims.get("userId");
	}
    
    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }
    
    public boolean validate(String token) {
    	boolean isValid = true;
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException ex) {
        	log.error("Invalid JWT signature, {}", ex);
            throw new ApiKeySignatureException("Invalid JWT signature", ex.getCause());
        } 
        return isValid;
    }
    
    private String removeBearerFromToken(String token) {
    	if(token.contains("Bearer "))
    		return token.replace("Bearer ", "");
    	return token;
    }
}
