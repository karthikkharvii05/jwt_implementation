package com.jsp.jwt_implementation.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.jwt_implementation.entity.UserInfo;
import com.jsp.jwt_implementation.service.UserInfoService;

@RestController
public class UserInfoController {

	private final UserInfoService userInfoService;
	
	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@PostMapping("/save")
	public UserInfo saveUser(@RequestBody UserInfo userInfo) {
		return userInfoService.saveUser(userInfo);
	}
	
	@PostMapping("/verify")
	public String verifyUser(@RequestBody UserInfo userInfo) {
		return userInfoService.verifyUser(userInfo);
	}
}
