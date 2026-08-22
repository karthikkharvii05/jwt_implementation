package com.jsp.jwt_implementation.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secretKey;

	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getSecreteKey())
				.compact();
	}

	private Key getSecreteKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_16));
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey)getSecreteKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public Date extractExpiration(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey)getSecreteKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getExpiration();
	}
	
	public boolean isExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean isverified(String token, String username) {
		String uname = extractUsername(token);
		return uname.equals(username) && !isExpired(token);
	}

}
