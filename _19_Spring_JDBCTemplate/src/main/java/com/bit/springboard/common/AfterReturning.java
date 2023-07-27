package com.bit.springboard.common;

import org.aspectj.lang.JoinPoint;

import com.bit.springboard.dto.BoardDTO;

public class AfterReturning {
	//returnObj: 포인트컷 메소드가 종료될 때 반환해주는 값
	//두 번째 매개변수인 Object를 바인드 변수라고 하고 메소드의 리턴 값을 자동으로 매핑
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		
		if(returnObj != null && returnObj instanceof BoardDTO) {
			BoardDTO board = (BoardDTO)returnObj;
			if(board.getBoardWriter().equals("관리자")) {
				System.out.println("관리자가 작성한 글입니다.");
			}
		}
		
		if(returnObj != null)
			System.out.println("[사후 처리] " + methodName 
						+ "()의 리턴 값: " + returnObj.toString());
		else
			System.out.println("[사후 처리] " + methodName 
						+ "()은 리턴 값이 없습니다.");
	}
	
	
	
	
	
	
	
	
}
