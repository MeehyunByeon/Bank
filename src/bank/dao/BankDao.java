package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bank.vo.Account;

public class BankDao {
	private static BankDao dao = new BankDao();
	private BankDao() {}
	public static BankDao getInstance() {
		return dao;
	}
	
	public Connection connect()
	{
		Connection conn = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankDB", "root", "1234");
		}catch(Exception e) {
			System.out.print("MDAO:connect"+e);
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt)
	{
		if(pstmt != null)
		{
			try {
				conn.close();
			}catch(Exception e) 
			{
				System.out.print("Pstmt close error"+e);
			}
		}
		
		if(conn != null)
		{
			try {
				conn.close();
			}catch(Exception e) 
			{
				System.out.print("Conn close error"+e);
			}
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{
		if(rs != null)
		{
			try {
				rs.close();
			}catch(Exception e) {
				System.out.print("rs close error"+e);
			}
		}
	}
	
	public void join(Account account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("insert into account values(?,?,?);");
			pstmt.setString(1, account.getId());
			pstmt.setString(2, account.getPwd());
			pstmt.setString(3, account.getMoney()+"");
			pstmt.executeUpdate();
			
		}catch (Exception e) 
		{
			System.out.print("Join Error: "+e);
		}finally {
			close(conn, pstmt);
		}
	}
	
	public boolean login(String id, String pwd) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select * from account where id = ? and passwd = ?;");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				result = true;
			}
			else
				result = false;
			
		}catch (Exception e) 
		{
			System.out.print("Login Error: "+e);
		}finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	public int deposit(String id, int money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				moneyDB = rs.getInt(1);
			}
			money = moneyDB + money;
			
			pstmt = conn.prepareStatement("update account set money=? where id = ?;");
			pstmt.setString(1, money+"");
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		}catch (Exception e) 
		{
			System.out.print("Deposit Error: "+e);
		}finally {
			close(conn, pstmt);
		}
		
		return money;
	}
	public int withdrawal(String id, int money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				moneyDB = rs.getInt(1);
			}
			if(money > moneyDB)
			{
				return -1;
			}
			money = moneyDB - money;
			
			pstmt = conn.prepareStatement("update account set money=? where id = ?;");
			pstmt.setString(1, money+"");
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		}catch (Exception e) 
		{
			System.out.print("Withdrawal Error: "+e);
		}finally {
			close(conn, pstmt);
		}
		
		return money;
	}
	public int query(String id) {
		int money = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				money = rs.getInt(1);
			}
			
		}catch (Exception e) 
		{
			System.out.print("Query Error: "+e);
		}finally {
			close(conn, pstmt, rs);
		}
		return money;
		
	}
	public boolean search(String id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select id from account where id = ?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				result = true;
			}
			
		}catch (Exception e) 
		{
			System.out.print("Search Error: "+e);
		}finally {
			close(conn, pstmt, rs);
		}
		
		return result;
	}
	public int transfer(String id, String rId, int money) {
		int tMoney = this.withdrawal(id, money);
		if(tMoney < 0)
			return tMoney;
		this.deposit(rId, money);
		return tMoney;
	}
}




