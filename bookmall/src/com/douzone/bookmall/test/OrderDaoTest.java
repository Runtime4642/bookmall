package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.OrderVo;


public class OrderDaoTest {

	public static void main(String[] args) {
		getListTest();

	}
	public static void getListTest() {
		List<OrderVo> list = new ArrayList<OrderVo>();
		list = new OrderDao().getList();
		for(OrderVo vo : list)
		{
			System.out.println(vo);
		}
	}
}
