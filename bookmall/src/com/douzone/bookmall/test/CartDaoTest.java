package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;



public class CartDaoTest {

	public static void main(String[] args) {
		
		getListTest();	
	}
	public static void getListTest() {
		List<CartVo> list = new ArrayList<CartVo>();
		MemberVo vo = new MemberVo();
		vo.setNo(1);
		list = new CartDao().getList(vo);
		for(CartVo vo2 : list)
		{
			System.out.println(vo2);
		}
	}
}
