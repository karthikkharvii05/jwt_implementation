package com.jsp.jwt_implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.jwt_implementation.entity.UserInfo;
import com.jsp.jwt_implementation.repository.UserInfoRepository;

@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserInfo saveUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		return userInfoRepository.save(userInfo);
	}
}
