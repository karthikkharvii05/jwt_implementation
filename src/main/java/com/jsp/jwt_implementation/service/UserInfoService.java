package com.jsp.jwt_implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.jwt_implementation.entity.UserInfo;
import com.jsp.jwt_implementation.repository.UserInfoRepository;

@Service
public class UserInfoService {

	private final UserInfoRepository userInfoRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager manager;
	private final JwtService jwtService;
	
	
	
	public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager manager, JwtService jwtService) {
		this.userInfoRepository = userInfoRepository;
		this.passwordEncoder = passwordEncoder;
		this.manager = manager;
		this.jwtService = jwtService;
	}

	public UserInfo saveUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		return userInfoRepository.save(userInfo);
	}

	public String verifyUser(UserInfo userInfo) {
		Authentication authentication = manager.authenticate(
				new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken();
		}
		return "Not Authenticated";
	}
}
