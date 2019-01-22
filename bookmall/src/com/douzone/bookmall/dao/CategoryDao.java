package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CategoryVo;


public class CategoryDao extends Dao {
	
	public List<CategoryVo> getList(){
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from category";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String category = rs.getString(2);
				
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setCategory(category);
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


	public boolean insertCategory(CategoryVo categoryVo)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into category values(null,?)";

			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1,categoryVo.getCategory());
			 int count = pstmt.executeUpdate();
			result = count ==1;
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
	
	public boolean insertCategory(String category,Long no)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into category values(?,?)";

			 pstmt = con.prepareStatement(sql);
			 pstmt.setLong(1,no);
			 pstmt.setString(2,category);
			 int count = pstmt.executeUpdate();
			result = count ==1;
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
	
	
}
