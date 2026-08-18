package com.jsp.jwt_implementation.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.jwt_implementation.entity.UserInfo;
import com.jsp.jwt_implementation.repository.UserInfoRepository;

@Service
public class SecurityDetails implements UserDetailsService {

	private final UserInfoRepository infoRepository;

	public SecurityDetails(UserInfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = infoRepository.findByUsername(username);
		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}
}
