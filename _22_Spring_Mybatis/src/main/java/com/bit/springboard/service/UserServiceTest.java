package com.bit.springboard.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.bit.springboard.dto.UserDTO;

public class UserServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("root-context.xml");
		
		UserService userService = 
				(UserService)factory.getBean("userService");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId("aaa");
		userDTO.setUserPw("1111");
		userDTO.setUserName("bbb");
		userDTO.setUserEmail("ccc");
		userDTO.setUserTel("ddd");
		
		userService.join(userDTO);
		
		UserDTO getUser = userService.getUser(1);
		
		System.out.println(getUser);
		
		factory.close();
		
		
		
		
		
		
		
		
		
		
		
	}

}
