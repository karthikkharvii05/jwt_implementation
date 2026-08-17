package com.jsp.jwt_implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.jwt_implementation.entity.UserInfo;
import com.jsp.jwt_implementation.service.UserInfoService;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;
	
	@PostMapping
	public UserInfo saveUser(@RequestBody UserInfo userInfo) {
		return userInfoService.saveUser(userInfo);
	}
}
