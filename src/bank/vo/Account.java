package bank.vo;

public class Account {
	private String id;
	private String pwd;
	private int money;
	
	public Account(String tId, String tPwd)
	{
		this.id = tId;
		this.pwd = tPwd;
		this.money = 0;
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String tid)
	{
		this.id = tid;
	}
	
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String tpwd)
	{
		this.pwd = tpwd;
	}
	
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int tmoney)
	{
		this.money = tmoney;
	}
}
