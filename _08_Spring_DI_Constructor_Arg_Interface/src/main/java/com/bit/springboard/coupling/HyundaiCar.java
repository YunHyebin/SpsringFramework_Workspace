package com.bit.springboard.coupling;

import org.springframework.stereotype.Component;

@Component
public class HyundaiCar implements Car {
	//의존 관계 생성
	//HyundaiCar가 CarAudio에 의존하는 관계
	//-> CarAudio의 내용이 변경되면 HyundaiCar의 결과가 바뀐다.
//	SonyCarAudio sonyCarAudio;
//	AppleCarAudio appleCarAudio;
	//인터페이스를 통한 상속
	//다형성을 통한 객체 생성
	CarAudio carAudio;
	
	String model;
	public HyundaiCar() {
		System.out.println("HyundaiCar 기본생성자 호출");
	}
	
	//id가 carAudio인 new CarAudio(); 객체 참조
	public HyundaiCar(CarAudio carAudio) {
		this.carAudio = carAudio;
	}
	
	public HyundaiCar(CarAudio carAudio, String model) {
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public void volumeUp() {
		this.carAudio.volumeUp();
	}
	
	public void volumeDown() {
		this.carAudio.volumeDown();
	}
}
