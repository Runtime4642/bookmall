package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertTest("인문");
		getListTest();
		

	}
	public static void getListTest() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list = new CategoryDao().getList();
		for(CategoryVo vo : list)
		{
			System.out.println(vo);
		}
	}
	public static void insertTest(String category)
	{
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCategory(category);
		new CategoryDao().insertCategory(categoryVo);
	}
}
