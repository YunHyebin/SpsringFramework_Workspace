package com.bit.springboard.dto;

public class Criteria {
	//현재 보고있는 페이지 번호
	private int pageNum;
	//한 페이지에 표출할 게시글의 수
	private int amount;
	//페이지 표출 시작 번호
	private int startNum;
	
	//생성자
	public Criteria() {
		//페이지번호와 개수가 지정이 안되었을 때 기본값
		this(1, 10);
	}
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	@Override
	public String toString() {
		return "Creteria [pageNum=" + pageNum + ", amount=" + amount + ", startNum=" + startNum + "]";
	}

	
	
}
