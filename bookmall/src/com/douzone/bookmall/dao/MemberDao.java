package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.douzone.bookmall.vo.MemberVo;


public class MemberDao extends Dao{
	
	
	
	public List<MemberVo> getList(){
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from member";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String id = rs.getString(2);
				String name = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String password = rs.getString(6);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setId(id);
				vo.setName(name);
				vo.setNumber(number);
				vo.setEmail(email);
				vo.setPassword(password);
				
				
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

	
	public boolean insertMember(MemberVo memberVo)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		boolean result=false;
		try {

			 con = getConnection();
			
			 
			 String sql = "insert into member values(null,?,?,?,?,?)";

			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1,memberVo.getId());
			 pstmt.setString(2,memberVo.getName());
			 pstmt.setString(3,memberVo.getNumber());
			 pstmt.setString(4,memberVo.getEmail());
			 pstmt.setString(5,memberVo.getPassword());
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
	
	public boolean deleteMember(String id)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
		
			 con = getConnection();
			
			//3. SQL문 준비
			 String sql="select id from member where id='"+id+"'";
			 stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 String sqlId = rs.getString(1);
				 String sql2="delete from member where id=?";
				 
					pstmt =con.prepareStatement(sql2);
					
					//4. binding					
					pstmt.setString(1,sqlId);
					
					int count= pstmt.executeUpdate();
					result = count==1;	
					return result;
			 }
			 System.out.println("해당 아이디인 회원이 없습니다.");
			 
			
			
			
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
	public boolean updateStatus(String id,String name)
	{
		Connection con =null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		try {
		
			 con = getConnection();
			
			//3. SQL문 준비
			 String sql="select id from member where id='"+id+"'";
			 stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 sql="update member set name=? where id=?";
				 
					pstmt =con.prepareStatement(sql);
					
					//4. binding
					
					pstmt.setString(1,name);
					pstmt.setString(2, id);
					int count= pstmt.executeUpdate();
					result = count==1;	
					return result;
			 }
			 System.out.println("해당 아이디인 회원이 없습니다.");
			 
			
			
			
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
	

	public MemberVo loginMember(String id,String pw)
	{		
		Statement stmt = null;
		Connection con =null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select * from member where id='"+id+"' and password='"+pw+"'";
			rs = stmt.executeQuery(sql);
			//5 결과 가져오기
			while(rs.next())
			{
				Long no = rs.getLong(1);
				 id = rs.getString(2);
				String name = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String password = rs.getString(6);
				
				 vo = new MemberVo();
				vo.setNo(no);
				vo.setId(id);
				vo.setName(name);
				vo.setNumber(number);
				vo.setEmail(email);
				vo.setPassword(password);
				
				
				return vo;
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
		
		return vo;
	}

	public boolean searhID(String id)
	{
		Statement stmt = null;
		PreparedStatement pstmt =null;
		Connection con =null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			 con = getConnection();		
			//3. Statment 객체를 생성
			stmt = con.createStatement();
			//4 sql 문 실행
			String sql = "select id from member where id='"+id+"'";
			
			//5 결과 가져오기
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				return true;

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
		
		return false;
	}
}
