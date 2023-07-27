package com.bit.springboard.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.bit.springboard.dto.BoardFileDTO;

public class FileUtils {
	//MultipartFile 객체를 받아서 DTO 형태로 변경 후 리턴하는 메소드
	public static BoardFileDTO parseFileInfo(MultipartFile file, String attachPath) throws IOException {
		//리턴할 boardDTO 객체 생성
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		
		String boardFileOrigin = file.getOriginalFilename();
		
		//같은 파일명이 업로드됐을 때 나중에 업로드 된 파일로 덮어씌워지기 때문에
		//파일명을 날짜_랜덤_.. 으로 만들기
		SimpleDateFormat formmater = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowDate = new Date();
		String nowDateStr = formmater.format(nowDate);
		
		//UUID로 랜덤을 바로 쓸 수 있다.
		UUID uuid = UUID.randomUUID();
		
		//실제 db에 저장될 파일명 지정
		String boardFileName = nowDateStr + "_" + uuid.toString() + "_" + boardFileOrigin;
		
		//파일경로 지정
		String boardFilePath = attachPath;
		
		//이미지인지 아닌지 검사
		File checkFile = new File(boardFileOrigin);
		//파일의 형식 가져오기
		String type = Files.probeContentType(checkFile.toPath());
		
		if(type != null) {
			if(type.startsWith("image")) {
				boardFileDTO.setBoardFileCate("image");
			}else {
				boardFileDTO.setBoardFileCate("etc");
			}
		}else {
			boardFileDTO.setBoardFileCate("etc");
		}
		
		//파일 업로드 처리
		File uploadFile = new File(attachPath + boardFileName);
		
		//리턴될 DTO 세팅
		boardFileDTO.setBoardFileName(boardFileName);
		boardFileDTO.setBoardFileOrigin(boardFileOrigin);
		boardFileDTO.setBoardFilePath(boardFilePath);
		
		//transfer
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardFileDTO;
		
	}
}