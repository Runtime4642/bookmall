package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookOrderVO;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.MemberVo;

public class BookOrderDao extends Dao {
	
	public List<BookOrderVO> getList(MemberVo memberVo){
		List<BookOrderVO> list = new ArrayList<BookOrderVO>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		Long memberNo=memberVo.getNo();
		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select o1.no, b.title,"
					+ " o1.number,b.price*o1.number from (order_book o1 join orderlist o2 on o1.order_no = o2.no) join book b on o1.book_no = b.no where o2.member_no="+memberNo;
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long number = rs.getLong(3);
				Long price = rs.getLong(4);
				
				
				
				BookOrderVO vo = new BookOrderVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setNumber(number);
				
				list.add(vo);

			}
			return list;
			
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception:"+e);
		} 
		finally
		{
		
				try {
					if(con!=null)
					con.close();
					if(stmt!=null)
						stmt.close();
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		
		return null;
	}

	

}
