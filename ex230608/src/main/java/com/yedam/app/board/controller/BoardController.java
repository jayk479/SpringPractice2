package com.yedam.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardServiceImpl;
	//컨트롤러에서는 기본적으로 매퍼존재xxx
	//기본적으로 컨트롤러 서비스 매퍼가 셋이 돌아야 됨ㅇㅇ
	//셋 다 지원하는 부분들이 다르기 때문에
	//그럼 뭐가 어디에 지원함 
	
	// 전체조회 : URI - boardList, RETURN - board/boardList
	@GetMapping("boardList")
	public String getBoardAllList(Model model) {
		// model.addAttribute("boardList", boardServiceImpl.getBoardList());
		
		List<BoardVO> list = boardServiceImpl.getBoardList();
		model.addAttribute("boardList", list);
		return "board/boardList"; // == 타일즈를적용하겠다
	}
	
	// 단건조회 : URI - boardInfo, RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String getBoardInfo(Model model, BoardVO boardVO) { // 커맨드캑체로 받아도 requestParameter로 받아도 상관xx but bno, writer를 받기 때문에..
		BoardVO vo = boardServiceImpl.getBoardInfo(boardVO);
		model.addAttribute("board", vo);
		return "board/boardInfo";
	}

	
	// 등록 - 페이지 : URI - boardInsert, RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		//만약 페이지를 열 때 꼭 들어가야 하는 데이터가 있다면
		//페이지 구상 시 넣어줘야 됨ㅇㅇ
		
		return "board/boardInsert";
	}

	
	// 등록 - 처리 : URI - boardInsert, RETURN - 전체조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsert(BoardVO boardVO, Model model) {// Model model 이거 왜 넣었어? 들어가야 하는 데이터가 있다면 페이지 구상시 넣어줘야 됩니다?
		boardServiceImpl.insertBoardInfo(boardVO);
		return "redirect:boardList"; // 해당 컨트롤러 재호출
	}

	
	// 수정 - 페이지 : URI - boardUpdate, RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(Model model, BoardVO boardVO) {
		//model.addAttribute("board", boardServiceImpl.getBoardInfo(boardVO));
		
		BoardVO vo = boardServiceImpl.getBoardInfo(boardVO);
		model.addAttribute("board", vo);
		return "board/boardUpdate";
	}

	// 수정 - 처리 : URI - boardUpdate, RETURN - 성공여부만 반환
	@PostMapping("boardUpdate")
	@ResponseBody // 페이지가 아니라 데이터를 반환하겠음ㅇㅇ
	public Map<String, Object> boardUpdate(BoardVO boardVO){
		boolean result = false;
		int boardNo = boardServiceImpl.updateBoardInfo(boardVO);
		if(boardNo > -1) {
			result  = true;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("board_no", boardNo);
		
		return map;
	}
//	public String boardUpdate(BoardVO boardVO) {
//		boardServiceImpl.updateBoardInfo(boardVO);	
//		return "redirect:boardList";
//	}
	
	
	// 삭제 : URI - boardDelete, RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelet(@RequestParam(required = false, defaultValue = "0", name = "bno") int boardNo) { 
		//@RequestParam를 사용하는 순간 해당 값이 무조건 들어와야 됨ㅇㅇ
		//=> 매개변수가 안 들어왔을 때의 처리 설정해줘야 됨
		//name = "bno" 매개변수명과 컬럼명과 차이 있을 때 사용함
		
		boardServiceImpl.deleteBoardInfo(boardNo);
		return "redirect:boardList";
	}
//	@GetMapping("boardDelete")
//	public String boardDelet(int boardNo) { 
//		boardServiceImpl.deleteBoardInfo(boardNo);
//		return "redirect:boardList";
//	}

}
