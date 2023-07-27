package com.bit.springboard.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bit.springboard.dto.BoardDTO;

public class BoardRowMapper implements RowMapper<BoardDTO> {
	public BoardDTO mapRow(ResultSet rs, int rowNum) {
		BoardDTO boardDTO = new BoardDTO();
		
		try {
			boardDTO.setBoardNo(rs.getInt("BOARD_NO"));
			boardDTO.setBoardTitle(rs.getString("BOARD_TITLE"));
			boardDTO.setBoardContent(rs.getString("BOARD_CONTENT"));
			boardDTO.setBoardWriter(rs.getString("BOARD_WRITER"));
			boardDTO.setBoardRegdate(rs.getDate("BOARD_REGDATE"));
			boardDTO.setBoardCnt(rs.getInt("BOARD_CNT"));
		} catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		
		return boardDTO;
		
		
		
		
		
		
		
		
	}
}
