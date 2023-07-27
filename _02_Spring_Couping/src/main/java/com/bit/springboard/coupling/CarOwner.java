package com.bit.springboard.coupling;

public class CarOwner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//의존성이 두개가 생기면서 결합도가 높아진다.
		//또 다른 클래스가 만들어지면 계속해서 결합도는 높아진다.
		KiaCar kCar = new KiaCar();
		HyundaiCar hCar = new HyundaiCar();
		
		kCar.engineOn();
		kCar.speedUp();
		kCar.speedDown();
		kCar.EngineOff();
		
		hCar.engineOn();
		hCar.speedUp();
		hCar.speedDown();
		hCar.EngineOff();
		
	}

}
