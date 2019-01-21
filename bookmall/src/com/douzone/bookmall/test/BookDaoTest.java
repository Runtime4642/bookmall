package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;


public class BookDaoTest {

	public static void main(String[] args) {
		System.out.println("======insert======");
		insertTest("고요할수록 밝아지는 것들",13500,"혜민",1,10);
		getListTest();
	}
	public static void getListTest() {
		List<BookVo> list = new ArrayList<BookVo>();
		list = new BookDao().getList();
		for(BookVo vo : list)
		{
			System.out.println(vo);
		}
	}
	public static void insertTest(String title,long price,String author,long categoryNo,long stock)
	{
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setAuthor(author);
		vo.setCategoryNo(categoryNo);
		vo.setStock(stock);
		new BookDao().insertBook(vo);
	}
	
}
