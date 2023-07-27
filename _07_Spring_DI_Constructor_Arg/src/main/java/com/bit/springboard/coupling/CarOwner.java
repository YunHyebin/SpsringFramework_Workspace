package com.bit.springboard.coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CarOwner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. spring container 가동
		//스프링 컨테이너 객체 생성
		//bean 태그로 등록되어있는 클래스의 객체 자동 생성(bean객체)
		//스프링 컨테이너에 의해서 객체 자동생성 시 init-method에 패이된 메소드 호출
		AbstractApplicationContext factory =
				//어떤 설정파일을 가지고 스프링 컨테이너를 가동할 지 지정
				new GenericXmlApplicationContext("root-context.xml");
		
		//2. 의존성 검색(DL: Dependency Lookup)&
		//   의존성 주입(DI: Dependency Injection)
		//의존성 주입: 의존성 검색을 통해 찾은 객체를 변수에 담아줌.
		//     getBean이 의존성 검색: id 값으로 생성되어있는 bean객체 검색
		Car car = (KiaCar)factory.getBean("kCar");
		car.engineOn();
		car.speedUp();
		car.speedDown();
		car.engineOff();
		
		//init-method로 초기화된 model 값 출력.
		KiaCar kCar = (KiaCar)factory.getBean("kCar");
		System.out.println(kCar.getModel());
		
		//lazy-init true 설정 시 객체를 요청하는 시점에 객체 생성
		//scope="prototype"일 경우 객체를 요청(생성)할 떄마다 새로운 객체를 생성한다.
		// kiaCar의 scope를 prototype으로 지정했더니 kCar 객체를 요청할 때 새로운 객체가 생성되었다.
		//scope="singleton"은 기본값으로 하나의 객체를 공유한다.
		
		car = (Car)factory.getBean("hCar");
		car.engineOn();
		car.speedUp();
		car.speedDown();
		car.engineOff();
		
		
		HyundaiCar hCar = (HyundaiCar)factory.getBean("hCar");
		hCar.engineOn();;
		hCar.volumeUp();
		hCar.volumeDown();
		hCar.engineOff();
		
		//3. Spring Container의 종류
		//스프링 컨테이너가 종료되면서 factory에 생성되어있던 bean 객체들을 자동 삭제
		factory.close();
		
		
	}

}
