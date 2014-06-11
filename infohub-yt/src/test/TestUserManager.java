package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import manage.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rrapi.RrAccount;

public class TestUserManager {

	UserManager x;
	@Before
	public void setUp() throws Exception {
		x=new UserManager();
	}

	@After
	public void tearDown() throws Exception {
		x=null;
	}

	@Test
	public void checkUsername() throws SQLException {
		assertEquals(x.checkUsername("suning123"),true);
		assertEquals(x.checkUsername("suning"),false);
		assertEquals(x.checkUsername("yyk"),false);
		assertEquals(x.checkUsername("yt11"),true);
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void checkLogin() throws SQLException {
		assertEquals(x.checkLogin("suning","123"),"密码错误");
		assertEquals(x.checkLogin("suning","suning"),"80001");
		assertEquals(x.checkLogin("suning123","suning"),"用户名不存在");
		//fail("Not yet implemented");
	}
	
	@Test
	public void register() throws SQLException {
		int i=0;
		String username="testname"+i;
		while (!x.checkUsername(username)) {
			i=i+1;
			username="testname"+i;
		}
		x.register(username,"123","sn-40@163.com");
		//fail("Not yet implemented");
	}
	
	@Test
	public void getAccount() throws SQLException {
		
		
        RrAccount t= new RrAccount();
        
        for (int i=80001;i<80020;i++) {
			t=x.getAccount(""+i);
			if (t!=null) {
				System.out.print(i+"  ");
				System.out.print(t.getUsername()+"  ");
		        System.out.print(t.getPassword()+"\n");
			}
		}
		//fail("Not yet implemented");
	}
	
	@Test
	public void getAccountByName() throws SQLException {
		RrAccount t= new RrAccount();
		String uname[]=new String[4];
		uname[0]="suning123";
		uname[1]="suning";
		uname[2]="yyk";
		uname[3]="yt";
		
		for (int i=0;i<4;i++) 
			if (x.getAccountByName(uname[i])!=null) {
				t=x.getAccountByName(uname[i]);
				System.out.print(t.getUsername()+"  ");
		        System.out.print(t.getPassword()+"\n");	
			}
		
        /*t=x.getAccountByName("suning");
		System.out.print(t.getUsername()+"  ");
        System.out.print(t.getPassword()+"\n");
		
        t=x.getAccountByName("yyk");
		System.out.print(t.getUsername()+"  ");
        System.out.print(t.getPassword()+"\n");
		
        t=x.getAccountByName("yt");
		System.out.print(t.getUsername()+"  ");
        System.out.print(t.getPassword()+"\n");
		*/
		//fail("Not yet implemented");
	}
	
	@Test
	public void getInfo() throws SQLException {
		assertEquals(x.getInfo("LEARN","80005"),true);		
		assertEquals(x.getInfo("RENREN","80006"),true);
		assertEquals(x.getInfo("INFO","80007"),true);
		assertEquals(x.getInfo("CST","80008"),true);
		
		assertEquals(x.getInfo("RENREN","80005"),false);
		assertEquals(x.getInfo("INFO","80006"),false);
		assertEquals(x.getInfo("CST","80007"),false);
		assertEquals(x.getInfo("LEARN","80008"),false);
		//fail("Not yet implemented");
	}
	
	@Test
	public void modifyInfo() throws SQLException {
		x.modifyInfo("80005","LEARN","nnn","ppp","yes");		
		x.modifyInfo("80006","RENREN","nnn","ppp","yes");
		x.modifyInfo("80007","INFO","","","yes");
		x.modifyInfo("80008","CST","","","yes");
		
		x.modifyInfo("80005","RENREN","nnn","ppp","no");		
		x.modifyInfo("80006","INFO","nnn","ppp","no");
		x.modifyInfo("80007","CST","","","no");
		x.modifyInfo("80008","LEARN","","","no");
		
		//fail("Not yet implemented");
	}
	
}
