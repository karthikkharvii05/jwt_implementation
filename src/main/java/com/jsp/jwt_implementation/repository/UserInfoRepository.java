package com.jsp.jwt_implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.jwt_implementation.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	 UserInfo findByUsername(String username);
}
