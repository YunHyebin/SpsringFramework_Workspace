package com.bit.springboard.dto;

import java.sql.Date;

public class BoardDTO {
	private int BoardNo;
	private String BoardTitle;
	private String BoardContent;
	private String BoardWriter;
	private Date BoardRegdate;
	private int BoardCnt;
	
	public int getBoardNo() {
		return BoardNo;
	}
	public void setBoardNo(int boardNo) {
		BoardNo = boardNo;
	}
	public String getBoardTitle() {
		return BoardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		BoardTitle = boardTitle;
	}
	public String getBoardContent() {
		return BoardContent;
	}
	public void setBoardContent(String boardContent) {
		BoardContent = boardContent;
	}
	public String getBoardWriter() {
		return BoardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		BoardWriter = boardWriter;
	}
	public Date getBoardRegdate() {
		return BoardRegdate;
	}
	public void setBoardRegdate(Date boardRegdate) {
		BoardRegdate = boardRegdate;
	}
	public int getBoardCnt() {
		return BoardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		BoardCnt = boardCnt;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [BoardNo=" + BoardNo + ", BoardTitle=" + BoardTitle + ", BoardContent=" + BoardContent
				+ ", BoardWriter=" + BoardWriter + ", BoardRegdate=" + BoardRegdate + ", BoardCnt=" + BoardCnt + "]";
	}

	
}
