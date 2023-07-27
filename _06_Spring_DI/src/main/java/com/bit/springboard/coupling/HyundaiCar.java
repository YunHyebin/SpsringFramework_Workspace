package com.bit.springboard.coupling;

import org.springframework.stereotype.Component;

@Component
public class HyundaiCar implements Car {
	//의존 관계 생성
	//HyundaiCar가 CarAudio에 의존하는 관계
	//-> CarAudio의 내용이 변경되면 HyundaiCar의 결과가 바뀐다.
	CarAudio carAudio;
	
	String model;
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
		//메소드 호출할 때마다 객체가 계속 생성된다.
		//따라서 CarAudio 객체도 스프링 컨테이너에 생성하고 공유해서 쓸 수 있는 방식으로 변경이 이뤄져야 한다.
		//하나만 생성해서 공유하도록 bean객체로 생성하게끔.
		//의존성 주입
		carAudio = new CarAudio();
		carAudio.volumeUp();
	}
	public void volumeDown() {
		carAudio = new CarAudio();
		carAudio.volumeDown();
	}
}
