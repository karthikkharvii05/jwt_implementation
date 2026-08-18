package com.jsp.jwt_implementation.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

	public String generateToken() {
		return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwicGFzc3dvcmQiOiJLYXJ0aGlrQDA1Iiwicm9sZSI6IkFkbWluIn0.J1KFe32uzR8mN9NAhJgj0ts4_5uzzBvRfUk7QoSR7O8";
	}
}
