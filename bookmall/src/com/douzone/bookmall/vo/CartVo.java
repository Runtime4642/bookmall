package com.douzone.bookmall.vo;

public class CartVo {
	private long no;
	private long bookNo;
	private long membeNo;
	private long count;
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", book_no=" + bookNo + ", member_no=" + membeNo + ", count=" + count + "]";
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public long getMemberNo() {
		return membeNo;
	}
	public void setMemberNo(long membeNo) {
		this.membeNo = membeNo;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
		
	

}
