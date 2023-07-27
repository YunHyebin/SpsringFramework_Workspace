package com.bit.springboard.coupling;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("hCar")
public class HyundaiCar implements Car {
	
	//Autowired는 무조건 형태만 따진다.
//	@Autowired
//	@Qualifier("sony")
	//필요한 객체를 먼저 생성한다.
	//CarAudio의 객체가 먼저 생성된다.
	@Resource(name="sony")
	CarAudio carAudio;
	String model;
	
	public HyundaiCar() {
		System.out.println("HyundaiCar 기본생성자 호출");
	}
	
	//id가 carAudio인 new CarAudio(); 객체 참조
	public HyundaiCar(CarAudio carAudio) {
		this.carAudio = carAudio;
	}
	
	//생성자 주입
	public HyundaiCar(@Autowired @Qualifier("sony") CarAudio carAudio, String model) {
		//root-context에서 bean으로 생성된 객체를 의존성으로 주입
		this.carAudio = carAudio;
		//현대가 객체가 생성되면서 현대카 안에 carAudio 변수는 할당(초기화, 객체가 주입, 의존성이 주입)가 된 상태.
		this.model = model;
	}
	
	//bean init-method에서 사용할 메소드 선언
	public void initMethod() {
		this.model = "아반떼";
		System.out.println("HyundaiCar 객체 생성");
	}
	public void destroyMethod() {
		this.model = "";
		System.out.println("HyundaiCar 객체 삭제 전 동작");
	}
	@Override
	public void engineOn() {
		System.out.println("HyundaiCar 시동을 건다");
	}
	@Override
	public void engineOff() {
		System.out.println("HyundaiCar 시동을 끈다");
	}
	@Override
	public void speedUp() {
		System.out.println("HyundaiCar 속도를 올린다");
	}
	@Override
	public void speedDown() {
		System.out.println("HyundaiCar 속도를 내린다");
	
		
	}
	
	//getter&setter Method
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		System.out.println("setModel 호출");
		this.model = model;
	}
	
	public CarAudio getCarAudio() {
		return carAudio;
	}
	
	public void setCarAudio(CarAudio carAudio) {
		//setter 메소드를 통한 의존성 주입
		System.out.println("setCarAudio 호출");
		this.carAudio = carAudio;
	}
	
	public void volumeUp() {
		this.carAudio.volumeUp();
	}
	
	public void volumeDown() {
		this.carAudio.volumeDown();
	}
}
