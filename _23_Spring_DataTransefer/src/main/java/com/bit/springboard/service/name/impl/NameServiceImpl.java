package com.bit.springboard.service.name.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.springboard.dto.NameDTO;
import com.bit.springboard.service.name.NameService;

//바로 nameServiceImpl이란 객체 생성되도록
@Service("nameServiceImpl")
public class NameServiceImpl implements NameService{
//NameService 인터페이스를 구현하는 NameServiceImpl
	
	//NameDAO 의존성 주입
	@Autowired
	private NameDAO nameDAO;
	
	@Override
	public void insertName(NameDTO nameDTO) {
		nameDAO.insertName(nameDTO);
		
	}

	@Override
	public List<NameDTO> getNameList() {
		// TODO Auto-generated method stub
		return nameDAO.getNameList();
	}

}
