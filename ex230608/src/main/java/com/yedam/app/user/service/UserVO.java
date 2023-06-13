package com.yedam.app.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails {
	
	private String id;
	private String pwd;
	private String role;
	
	
	
	// 이걸 UserVO로 쓰지 말자
	// json으로 처리 할 때 문제 생길 수 있음
	// 사용자의 정보가 필요할 때 해당 클래스를 보내겠지?
	// => 이 클래스가 json으로 넘어 갈 때 getAuthorities 이게 문제가 됨ㅇㅇ
	// 통신에서 문제 발생 == vo는 따로 만들고 필드로 집어 넣기
	// cuz 저거 return타입이 collection임
	// 굳이 쓸 거라면 jackson ignore 어노테이션 붙여주기
	
	//ex) private MemberVO member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
	     auth.add(new SimpleGrantedAuthority(this.role));
	     return auth;
	}

	@Override
	public String getPassword() {
		return pwd;
	}

	@Override
	public String getUsername() {
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
