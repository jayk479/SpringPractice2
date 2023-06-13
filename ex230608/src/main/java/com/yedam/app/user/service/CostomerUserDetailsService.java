package com.yedam.app.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yedam.app.user.mapper.UserMapper;


public class CostomerUserDetailsService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//인증, 인가에 대해 처리하는 부분임ㅇㅇ
		UserVO userVO = userMapper.getUser(username);
		
		// 해당 사용자 존재유무 확인
		if(userVO == null){
			throw new UsernameNotFoundException("no user");
		}
		
		// 비밀번호 확인
		
		
		// 권한 지정 별도로 처리ㅇㅇ
		// List<GrantedAuthority> auth = new ArrayList<>();
	    // auth.add(new SimpleGrantedAuthority(userVO.getRole()));
		
	     
		//return new UserVO(username, userVO.getPassword(),auth);
	     return userVO;
	}

}
