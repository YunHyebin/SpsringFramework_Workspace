package com.bit.springboard.coupling;

public class SonyCarAudio implements CarAudio{
	public SonyCarAudio() {
		System.out.println("sony carAudio 객체 생성");
	}
	@Override
	public void volumeUp() {
		System.out.println("SonyCarAudio 소리 증가");
		
	}

	@Override
	public void volumeDown() {
		System.out.println("SonyCarAudio 소리 감소");
		
	}

}
