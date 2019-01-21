package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;



public class CartDao extends Dao {
	
	public List<CartVo> getList(MemberVo memberVo){
		List<CartVo> list = new ArrayList<CartVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from cart where member_no="+memberVo.getNo();
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				Long bookNo = rs.getLong(2);
				Long memberNo = rs.getLong(3);
				Long count = rs.getLong(4);
				
				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setBookNo(bookNo);
				vo.setMemberNo(memberNo);
				vo.setCount(count);
				
				
				
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

	public boolean insertCart(CartVo cartVo)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into cart values(null,?,?,?)";

			 pstmt = con.prepareStatement(sql);
			 pstmt.setLong(1,cartVo.getBookNo());
			 pstmt.setLong(2,cartVo.getMemberNo());
			 pstmt.setLong(3,cartVo.getCount());
			 int count = pstmt.executeUpdate();
			return result = count ==1;
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
					if(pstmt!=null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		return result;
		
		
	}
	
	public BookVo getBookVo(CartVo cartVo)
	{
			Connection con =null;
			Statement stmt = null;
			ResultSet rs = null;
			Long memberNo=cartVo.getMemberNo();
			Long bookNo=cartVo.getBookNo();
			try {
				 con = getConnection();		
				//3. Statment 객체를 생성
				stmt = con.createStatement();
				//4 sql 문 실행
				String sql = "select * from book where no="+bookNo;
				rs = stmt.executeQuery(sql);
				
				//5 결과 가져오기
				while(rs.next())
				{
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					Long price = rs.getLong(3);
					String author = rs.getString(4);
					Long categoryNo=rs.getLong(5);
					Long stock = rs.getLong(6);
					
					BookVo bookVo = new BookVo();
					bookVo.setNo(no);
					bookVo.setTitle(title);
					bookVo.setPrice(price);
					bookVo.setAuthor(author);
					bookVo.setCategoryNo(categoryNo);
					bookVo.setStock(stock);
					return bookVo;
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
	
			return null;
	}


	public boolean cartDelete(Long cartNo)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
		
			 con = getConnection();
			
			//3. SQL문 준비
			 String sql="select no from cart where no='"+cartNo+"'";
			 stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 String sqlId = rs.getString(1);
				 String sql2="delete from cart where no=?";
				 
					pstmt =con.prepareStatement(sql2);
					
					//4. binding					
					pstmt.setString(1,sqlId);
					
					int count= pstmt.executeUpdate();
					result = count==1;	
					return result;
			 }
			 System.out.println("해당 번호의 장바구니가 없습니다.");
			 
			
			
			
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

}
