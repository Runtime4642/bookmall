package com.douzone.bookmall.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.BookOrderDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.Dao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.BookOrderVO;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderVo;


public class BookMall {

	public static void main(String[] args) {
		
		//기본 로그인 계정 : id : asdf2022 pw: 123
		startSet();
		menu();	
		endSet();
		
		//스캐너 입력 오류 등으로 오류 종료시 endSet 매소드만 실행시킨뒤 다시 프로그램 실행시키면 됩니다.
		//startset() 은 초기 입력값 책,회원,카테고리 등의 입력을 합니다.
		//endset() 은 데이터베이스 안의 모든 데이터를 삭제 합니다.
		//처음에 장바구니와 주문목록은 비어있습니다.
		//주문하기에 가면 장바구니에 넣기와 주문하기가 있고
		// 장바구니에 담거나 주문하기로 주문을하면 주문리스트 출력시 출력됩니다.
		
	}
	public static void categoryInsert(String category)
	{
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCategory(category);
		new CategoryDao().insertCategory(categoryVo);
	}
	public static void bookInsert(String title,long price,String author,long categoryNo,long stock)
	{
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setAuthor(author);
		vo.setCategoryNo(categoryNo);
		vo.setStock(stock);
		new BookDao().insertBook(vo);
	}
	public static void memberJoin()
	{
		
		String id, name, email,number, password;
		
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.print("아이디를 입력하세요: ");
		id=sc.nextLine();
		if(new MemberDao().searhID(id))
			System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력하세요");
		else
			break;
		}
		System.out.print("비밀번호를 입력하세요: ");
		password=sc.nextLine();
		System.out.print("이름을 입력하세요: ");
		name=sc.nextLine();
		System.out.print("이메일을 입력하세요: ");
		email=sc.nextLine();
		System.out.print("전화번호를 입력하세요 ex)01012345678 : " );
		number=sc.nextLine();
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setName(name);
		memberVo.setEmail(email);
		memberVo.setNumber(number);
		memberVo.setPassword(password);
		new MemberDao().insertMember(memberVo);
		System.out.println("정상적으로 가입되었습니다.");
	}
	
	public static MemberVo login() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력하세요: ");
		String id =sc.nextLine();
		System.out.print("패스워드를 입력하세요: ");
		String pw = sc.nextLine();
		
		MemberVo vo = new MemberDao().loginMember(id, pw);
		if(vo==null)
			System.out.println("로그인 실패");
		else
			System.out.println("로그인 성공");
		
		return vo;
	}
	public static void menu() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while(loop) {
			
			
		System.out.println("1. 로그인  , 2. 회원가입 , 3.회원리스트 출력 , default. 종료");
		int input = sc.nextInt();
		switch (input) {
		case 1: 
			MemberVo vo = login();
			if(vo==null)
			{
				break;
			}
			else
			{
				memberMenu(vo);
			}
			break;
		case 2: 	
			memberJoin();
			break;
		case 3:
			memberPrint();
			break;
		default:
			loop=false;
			break;
		}
		}
	}
	public static void memberPrint() {
		List <MemberVo> list = new ArrayList<MemberVo>();
		list = new MemberDao().getList();
		System.out.println("===================회원 리스트 출력=========================");
		for(MemberVo vo : list)
			System.out.println(vo);
		System.out.println("==============================================");
		
	}
	public static void memberMenu(MemberVo vo)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("=====로그인 계정 정보======");
		System.out.println(vo);
		System.out.println("=====================");
		boolean loop = true;
		while(loop) {
		System.out.println("1.도서목록보기 , 2.장바구니 확인(주문) , 3.주문하기  ,4. 주문확인(리스트출력), 5.주문 도서 확인 default: 종료");
		int input =sc.nextInt();
		switch (input) {
		case 1: bookListPrint();	
			break;
		case 2: cartPrint(vo);
			break;
		case 3: order(vo);
			break;
		case 4: orderPrint(vo);	
			break;
		case 5: orderBookPrint(vo);
			break;
		default:
			loop =false;
			break;
		}
		}
	}
	public static void bookListPrint()
	{
		List<BookVo> list = new ArrayList<BookVo>();
		list = new BookDao().getList();
		System.out.println("===================도서 리스트 출력=========================");
		for(BookVo vo : list)
		{
			System.out.println("책번호:"+vo.getNo()+", 책 제목:"+vo.getTitle()+", 가격:"+vo.getPrice()+", 저자:"+vo.getAuthor()+
					", 카테고리 :"+new BookDao().getCategoryName(vo.getCategoryNo())+", 재고:"+vo.getStock());
			
		}
		System.out.println("========================================================");
	}
	public static void order(MemberVo vo) {
		Scanner sc = new Scanner(System.in);
		System.out.println("주문할 책번호를 입력하세요");
		int bookNo = sc.nextInt();
		System.out.println("주문할 개수를 입력하세요");
		int number = sc.nextInt();
		System.out.println("1. 바로 주문 , 2.장바구니에 담기 ");
		int select = sc.nextInt();
		if(select == 1)
		{
			System.out.println("배송지를 입력하세요");
			String address = sc.next();
			if(new OrderDao().order(bookNo,number,vo,address))
				System.out.println("주문성공");
			else
				System.out.println("주문실패");
		}
		else if(select ==2)
		{
			cartIn(bookNo,vo,number);
		}
		
	}
	public static void cartIn(long bookNo,MemberVo memberVo,long count) {
		CartVo cartVo = new CartVo();
		cartVo.setBookNo(bookNo);
		cartVo.setMemberNo(memberVo.getNo());
		cartVo.setCount(count);
		if(new CartDao().insertCart(cartVo))
			System.out.println("장바구니에 담겼습니다");
		else
			System.out.println("장바구니에 담기지 않았습니다");
	}
	public static void cartPrint(MemberVo memberVo) {
		List<CartVo> list = new ArrayList<CartVo>();
		list = new CartDao().getList(memberVo);
		System.out.println("===================장바구니 출력=========================");
		for(CartVo cartVo:list)
		{
			BookVo bookVo = new BookVo();
			bookVo =new CartDao().getBookVo(cartVo);
			System.out.println("장바구니 번호:"+cartVo.getNo()+", 책 번호:"+bookVo.getNo() + ", 책 제목:"+bookVo.getTitle()+", 주문 개수:"+cartVo.getCount()
			+", 현재 책 재고:"+bookVo.getStock()+", 총 가격:"+bookVo.getPrice()*cartVo.getCount());
			
		}
		System.out.println("===================================================");
		
		System.out.println("1.장바구니 상품들 주문  , 2.장바구니 삭제    , defalut. 돌아가기");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
			if(input==1)
			{
				System.out.println("배송지를 입력해주세요:");
				String address=sc.next();
			new OrderDao().order(list,address);
			}
			else if(input==2)
			{
				System.out.println("삭제할 장바구니 번호를 입력:");
				long no = sc.nextInt();
				if(new CartDao().cartDelete(no))
					System.out.println("삭제완료");
			}else
			{
				return;
			}
	}

	public static void orderPrint(MemberVo memberVo)
	{
		List<OrderVo> list = new ArrayList<OrderVo>();
		list = new OrderDao().orderPrint(memberVo);
		System.out.println("===================주문 목록 출력=========================");
		for(OrderVo vo : list)
		{
			System.out.println("주문 번호:"+vo.getNo()+", 주문 가격:"+vo.getPrice()+", 주문 상태:"+vo.getState()+", 배송지:"+vo.getAddress());
		}
		System.out.println("=======================================================");
		
	}

	public static void orderBookPrint(MemberVo memberVo)
	{
		List <BookOrderVO> list = new ArrayList<BookOrderVO>();
		list = new BookOrderDao().getList(memberVo);
		System.out.println("===================주문 도서 목록 출력=========================");
		for(BookOrderVO vo : list)
		{
			System.out.println("책제목:"+vo.getTitle()+", 구매권수:"+vo.getNumber()+", 가격:"+vo.getPrice());
		}
		System.out.println("=======================================================");
	}
	public static void memberJoin2()
	{
		MemberVo vo = new MemberVo();
		vo.setId("asdf2022");
		vo.setName("정영석");
		vo.setPassword("123");
		vo.setEmail("mana129@naver.com");
		vo.setNumber("01046428378");
		new MemberDao().insertMember(vo);
	}

	public static void startSet()
	{
		
		new CategoryDao().insertCategory("인문",(long)1);
		new CategoryDao().insertCategory("소설",(long)2);
		new CategoryDao().insertCategory("만화",(long)3);
		bookInsert("고요할수록 밝아지는 것들",13500,"혜민",1,100);
		bookInsert("곰돌이 푸 ,행복한 일은 매일 있어",10800,"곰돌이 푸",2,100);
		bookInsert("원피스19권",4000,"오다",3,100);
		memberJoin2();
	}
	public static void endSet()
	{
		new Dao().allDelete();
	}
}
