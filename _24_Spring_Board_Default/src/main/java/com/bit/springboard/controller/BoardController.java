package com.bit.springboard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springboard.dto.BoardDTO;
import com.bit.springboard.dto.UserDTO;
import com.bit.springboard.service.board.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 게시판 페이지로 이동
	//RequestMapping은 post, get 두 요청을 모두 받아서 실행한다.
	@RequestMapping("/getBoardList.do")
	public String getBoardListView(Model model, @RequestParam Map<String, String> paramMap) {
		// 화면을 표출하면서 바로 데이터 출력
		model.addAttribute("boardList", boardService.getBoardList(paramMap));
		
		//검색조건과 검색어 다시 화면단으로 전송
		if(paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
			model.addAttribute("searchCondition", paramMap.get("searchCondition"));
		}
		
		//총 게시글의 개수 구하기
		int baordTotal = boardService.getBoardTotal(paramMap);
		
		model.addAttribute("pageDTO", new PageDTO(cri, boardTotal));
		
		return "board/getBoardList";
	}

	// 새글 등록 화면으로 이동(get 방식)
	@GetMapping("insertBoard.do")
	public String insertBoardView(HttpSession session) {
		//로그인 된 정보가 없으면 로그인 화면으로 이동
		//세션에 담겨있는 사용자 정보 조ㅗ히
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/user/login.do";
		}
		return "board/insertBoard";
	}

	// 새글 등록 처리(post방식)
	// 등록 완료 후 게시글 목록 페이지로 이동
	@PostMapping("/insertBoard.do")
	public String insertboard(BoardDTO boardDTO) {
		boardService.insertBoard(boardDTO);
		return "redirect:/board/getBoardList.do";
		
		
	}
	
	@GetMapping("/updateBoardCnt.do")
	public String updateBoardCnt(@RequestParam("boardNo") int boardNo) {
		//조회수 올리기
		boardService.updateBoardCnt(boardNo);
		return "redirect:/board/getBoard.do?boardNo=" + boardNo;
	}
	
	@GetMapping("/getBoard.do")
	public String getBoard(@RequestParam("boardNo") int boardNo, Model model){
		model.addAttribute("board", boardService.getBoard(boardNo));
		
		return "board/getBoard";
	}
	
	@PostMapping("/updateBoard.do")
	public String updateBoard(BoardDTO boardDTO) {
		boardService.updateBoard(boardDTO);
//		return "redirect:/board/getBoardList";
		return "redirect:/board/getBoard.do?boardNo=" + boardDTO.getBoardNo(); 
	}
	
	
	//deleteBoard 화면으로 이동
	@GetMapping("/deleteBoard.do")
	public String deleteBoard(@RequestParam("boardNo") int boardNo) {
		//삭제하기
		boardService.deleteBoard(boardNo);
		//boardList 화면으로 이동
		return "redirect:/board/getBoardList.do";
	}
	
	
//	//getBoardListAjax 화면으로 이동
//	@GetMapping("/getBoardListAjax.do")
//	public String getBoardLIstAjaxView() {
//		return "board/getBoardListAjax";
//	}
//	//게시글 목록 json string으로 리턴
//	@PostMapping(value="/getBoardListAjax.do", produces="application/text; charset=UTF-8")
//	//리턴된데이터만 response body로 전송
//	@ResponseBody
//	public String getBoardLIstAjax() throws JsonProcessingException {
//		//json String을 생성하기 위한 ObjectMapper 객체 생성
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		//json 데이터 형식을 만들기 위한 Map 생성
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		
//		//jsonMap에 담아줄 게시글 목록 조회
//		List<BoardDTO> boardList = boardService.getBoardList();
//		
//		//게시글 목록 jsonMap에 담기
//		jsonMap.put("boardList", boardList);
//		
//		//jsonMap을 jsonString으로 변환
//		String jsonStr = objectMapper.writerWithDefaultPrettyPrinter()
//									 .writeValueAsString(jsonMap);
//		
//		//변환된 jsonStr리턴
//		return jsonStr;
//	}
	
	

}
