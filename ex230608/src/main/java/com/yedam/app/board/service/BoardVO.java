package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String title;
	private String contents;
	private String writer;
	
	// 값을 입력할 때 받아 오는, 들어가는 값에 대한 포맷배정
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date regdate; // yyyy/mm/dd 
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date updatedate;
	
	private String image;
	
}
