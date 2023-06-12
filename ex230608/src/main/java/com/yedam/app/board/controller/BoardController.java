package com.yedam.app.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardServiceImpl;
	
	// 전체조회 : URI - boardList, RETURN - board/boardList
	@GetMapping("boardList")
	public String getBoardAllList(Model model, BoardVO boardVO) {
		model.addAttribute("board", boardVO);
		model.addAttribute("boardList", boardServiceImpl.getBoardList());
		return "board/boardList";
	}
	
	// 단건조회 : URI - boardInfo, RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String getBoardInfo(Model model, BoardVO boardVO) {
		BoardVO vo = boardServiceImpl.getBoardInfo(boardVO);
		model.addAttribute("board", vo);
		return "board/boardInfo";
	}

	
	// 등록 - 페이지 : URI - boardInsert, RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}

	
	// 등록 - 처리 : URI - boardInsert, RETURN - 전체조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsert(BoardVO boardVO) {
		boardServiceImpl.insertBoardInfo(boardVO);
		return "redirect:boardList";
	}

	
	// 수정 - 페이지 : URI - boardUpdate, RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(Model model, BoardVO boardVO) {
		model.addAttribute("board", boardServiceImpl.getBoardInfo(boardVO));
		return "board/boardUpdate";
	}

	// 수정 - 처리 : URI - boardUpdate, RETURN - 성공여부만 반환
	@PostMapping("boardUpdate")
	public String boardUpdate(BoardVO boardVO) {
		int result = boardServiceImpl.updateBoardInfo(boardVO);
//		if(result > 0) {
//			return 
//		}
		return "success";
	}
	
	
	// 삭제 : URI - boardDelete, RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelet(int bno) {
		boardServiceImpl.deleteBoardInfo(bno);
		return "redirect:boardList";
	}

}
