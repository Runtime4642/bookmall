package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.MemberVo;


public class MemberDaoTest {

	public static void main(String[] args) {
		System.out.println("======insert======");
		insertTest("show","정영석","mana129@naver.com","01046428378","hi");
		getListTest();
		System.out.println("======update======");
		updateTest("show","양승우");
		getListTest();
		System.out.println("======delete======");
		deleteTest("show");
		getListTest();
		
	}
	public static void getListTest() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		list = new MemberDao().getList();
		for(MemberVo vo : list)
		{
			System.out.println(vo);
		}
	}
	public static void insertTest(
	  String id,
	  String name,
	  String email,
	  String number,
	  String password )
	{
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setName(name);
		memberVo.setEmail(email);
		memberVo.setNumber(number);
		memberVo.setPassword(password);
		new MemberDao().insertMember(memberVo);
	}

	public static void updateTest(String id,String name)
	{
		new MemberDao().updateStatus(id, name);
	}
	public static void deleteTest(String id)
	{
		new MemberDao().deleteMember(id);
	}

}
