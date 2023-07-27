package com.bit.springboard.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.UserDTO;

@Repository("userDAO")
public class UserDAO {
	//JDBC 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//쿼리문 작성
	private final String JOIN = "INSERT INTO T_USER ("
			+ "		USER_ID"
			+ "	  , USER_PW"
			+ "	  , USER_NAME"
			+ "	  , USER_EMAIL"
			+ "	  , USER_TEL"
			+ " ) VALUES ( "
			+ "	   ?"
			+ "	 , ?"
			+ "  , ?"
			+ "  , ?"
			+ "  , ?)";

	private final String GET_USER = "SELECT "
			+ "		ID"
			+ "   , USER_ID"
			+ "   , USER_PW"
			+ "   , USER_NAME"
			+ "   , USER_EMAIL"
			+ "   , USER_TEL"
			+ "   , USER_REGDATE"
			+ "  FROM T_USER"
			+ "  WHERE ID = ?";
	
	public void join(UserDTO userDTO) {
		// TODO Auto-generated method stub
		//여기서 userDTO에 담긴 내용을 insert
		System.out.println("------------UserDAO의 join 호출-------------");
		System.out.println("------------UserDAO의 userDTO-------------");
		System.out.println(userDTO);
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.prepareStatement(JOIN);
			
			stmt.setString(1, userDTO.getUserId());
			stmt.setString(2, userDTO.getUserPw());
			stmt.setString(3, userDTO.getUserName());
			stmt.setString(4, userDTO.getUserEmail());
			stmt.setString(5, userDTO.getUserTel());
			
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		System.out.println("------------UserDAO의 join 호출 종료-------------");
	}

	public UserDTO getUser(int id) {
		// TODO Auto-generated method stub
		System.out.println("------------UserDAO의 getUser 호출-------------");
		System.out.println("------------UserDAO의 id-------------");
		System.out.println(id);
		
		UserDTO userDTO = new UserDTO();
		
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.prepareStatement(GET_USER);
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				userDTO.setId(rs.getInt("ID"));
				userDTO.setUserId(rs.getString("USER_ID"));
				userDTO.setUserPw(rs.getString("USER_PW"));
				userDTO.setUserName(rs.getString("USER_NAME"));
				userDTO.setUserEmail(rs.getString("USER_EMAIL"));
				userDTO.setUserTel(rs.getString("USER_TEL"));
				userDTO.setUserRegdate(rs.getDate("USER_REGDATE"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		System.out.println("------------UserDAO의 getUser 호출종료-------------");
		return userDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
