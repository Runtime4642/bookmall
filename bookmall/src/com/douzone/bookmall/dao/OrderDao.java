package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderVo;


public class OrderDao extends Dao {
	
	
	public List<OrderVo> getList(){
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from orderlist";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				Long price = rs.getLong(2);
				String address = rs.getString(3);
				String state = rs.getString(4);
				Long memberNo = rs.getLong(5);
				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setPrice(price);
				vo.setAddress(address);
				vo.setState(state);
				vo.setMemberNo(memberNo);
				
				
				
				list.add(vo);
				//예시
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
			}
			
			
			
			
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
		
		return list;
	}

	public boolean order(long no,long number,MemberVo vo,String address)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
			
			 con = getConnection();
			
			//3. SQL문 준비
			 String sql="select stock,price from book where no="+no+" and stock >="+number;
			 stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 long stock = rs.getLong(1);
				 long price= rs.getLong(2)*number; //책값 * 사는 개수
				 sql="update book set stock=? where no=?";
				 
					pstmt =con.prepareStatement(sql);
					
					//4. binding
					
					pstmt.setLong(1,stock-number);
					pstmt.setLong(2, no);
					pstmt.executeUpdate();
				
					
					 
					//주문 테이블에 주문생성
					sql = "insert into orderlist values(null,?,?,'주문완료',?)";
					pstmt =con.prepareStatement(sql);
					pstmt.setLong(1,price);
					pstmt.setString(2, address);
					pstmt.setLong(3,vo.getNo());
					pstmt.executeUpdate();
					
					
					
					
						sql = "select max(no) from orderlist";
						ResultSet rs2 = stmt.executeQuery(sql);
						if(rs2.next()) {
						long orderNo =rs2.getLong(1);
					
					
					sql = "insert into order_book values(null,?,?,?)";
					pstmt =con.prepareStatement(sql);
					pstmt.setLong(1,number);
					pstmt.setLong(2, orderNo);
					pstmt.setLong(3,no);
					pstmt.executeUpdate();
					
					int count= pstmt.executeUpdate();
					result = count==1;	
					return result;
						}
			 }
			 System.out.println("해당 책이 없습니다.");
			 
			
			
			
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		return result;
	}
	
	public boolean order(List<CartVo> list,String address)
	{
		for (CartVo cartVo : list)
		{
			Long bookNo = cartVo.getBookNo();
			Long memberNo = cartVo.getMemberNo();
			Long count = cartVo.getCount();
			Connection con =null;
			Statement stmt = null;
			PreparedStatement pstmt = null;
			boolean result=false;
			try {
				
				 con = getConnection();
				
				//3. SQL문 준비
				 String sql="select stock,price from book where no="+bookNo+" and stock >="+count;
				 stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				 while(rs.next())
				 {
					 long stock = rs.getLong(1);
					 long price= rs.getLong(2)*count; //책값 * 사는 개수
					 sql="update book set stock=? where no=?";
					 
						pstmt =con.prepareStatement(sql);
						
						//4. binding
						
						pstmt.setLong(1,stock-count);
						pstmt.setLong(2, bookNo);
						pstmt.executeUpdate();
					
						
						 
						//주문 테이블에 주문생성
						sql = "insert into orderlist values(null,?,?,'주문완료',?)";
						pstmt =con.prepareStatement(sql);
						pstmt.setLong(1,price);
						pstmt.setString(2, address);
						pstmt.setLong(3,memberNo);
						pstmt.executeUpdate();
						
						
						
						
							sql = "select max(no) from orderlist";
							ResultSet rs2 = stmt.executeQuery(sql);
							if(rs2.next()) {
							long orderNo =rs2.getLong(1);
						
						
						sql = "insert into order_book values(null,?,?,?)";
						pstmt =con.prepareStatement(sql);
						pstmt.setLong(1,count);
						pstmt.setLong(2, orderNo);
						pstmt.setLong(3,bookNo);
						pstmt.executeUpdate();

						
						sql = "delete from cart where no = "+cartVo.getNo();
						
						
						int number= pstmt.executeUpdate();
						result = number==1;	
						return result;
							}
				 }
				 System.out.println("해당 책이 없습니다.");
				 
				
				
				
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
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
			
	
		}
		
		
		
		return false;
	}


	public List<OrderVo> orderPrint(MemberVo memberVo)
	{
		List <OrderVo> list = new ArrayList<OrderVo>();
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;
		Long memberNo = memberVo.getNo();

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select no,price,address,state from orderlist where member_no="+memberNo; 
					
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				long orderNo =rs.getLong(1);
				long orderPrice = rs.getLong(2);
				String orderaddress = rs.getString(3);
				String state = rs.getString(4);
				
				OrderVo orderVo = new OrderVo();
				orderVo.setNo(orderNo);
				orderVo.setPrice(orderPrice);
				orderVo.setAddress(orderaddress);
				orderVo.setState(state);
				list.add(orderVo);
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
