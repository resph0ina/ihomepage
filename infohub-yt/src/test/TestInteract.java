package test;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastruct.Data;

import b.CheckToB;
import b.CommentCB;
import b.InfoB;
import b.JiuJingB;
import b.RegCheckB;
import b.RenrenB;
import b.ShareCB;
import b.SubscribeToB;
import rrapi.RrAccount;

public class TestInteract {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInfoB() throws SQLException {
		//fail("Not yet implemented");
		InfoB c=new InfoB();
		ResultSet s=c.getInfoData();
		int x=0;
		while (s.next()) x++;
		assertEquals(x,44);	
		c=null;
	}
	@Test
	public void testRegCheckB() throws SQLException {
		//fail("Not yet implemented");
		RegCheckB c=new RegCheckB();
		String s=c.getID("suning","123","sn-40@163.com");
		assertEquals(s,null);
		int x=0;
		while (c.getID("suning"+x,"123","sn-40@163.com")==null)
			x++;
		c=null;
	}
	@Test
	public void testSubscribeToB() throws SQLException {
		//fail("Not yet implemented");
		SubscribeToB c=new SubscribeToB();
		boolean s;
		s=c.isOrder(1,"80007");
		assertEquals(s,true);
		s=c.isOrder(2,"80008");
		assertEquals(s,true);
		s=c.isOrder(3,"80005");
		assertEquals(s,true);
		s=c.isOrder(4,"80006");
		assertEquals(s,true);
		
		s=c.isOrder(2,"80007");
		assertEquals(s,false);
		s=c.isOrder(3,"80008");
		assertEquals(s,false);
		s=c.isOrder(4,"80005");
		assertEquals(s,false);
		s=c.isOrder(1,"80006");
		assertEquals(s,false);
		//-------------------------分割线------------------------------
		c.setOrder(1,"80009","","","no");
		c.setOrder(2,"80010","","","no");
		c.setOrder(3,"80011","n1","p1","yes");
		c.setOrder(4,"80012","n1","p1","yes");
		
		RrAccount t= new RrAccount();
		t=c.getRrAccount("80001");
		System.out.print(t.getUsername()+' '+t.getPassword());
		c=null;
	}
	
	@Test
	public void testCheckToBtoUserManager() throws SQLException {
		//fail("Not yet implemented");
		CheckToB c=new CheckToB();
		String x=c.getID("suning","suning");
		assertEquals(x,"80001");
		
	}
	
	@Test
	public void testCommentCBtoDataManager() throws SQLException {
		//fail("Not yet implemented");
		CommentCB c=new CommentCB();
		ResultSet s=c.getData("90");
		int x=0;
		while (s.next()) x++;
		assertEquals(x,6);
		Data v=new Data();
		v.setCommentID("55555");
		v.setContext("testCase");
		v.setId("77777");
		c.addComment(v);
		//???
		v.setCommentID("55555");
		v.setContext("testCase");
		v.setId("77777");
		c.addComment(v);
		
	}
	
	@Test
	public void testRenrenB() throws SQLException {
		//fail("Not yet implemented");
		RenrenB r=new RenrenB();
		r.checkPass("80001");
		r.getComment(0);
		r.addComment(-1, "testCase");
		r.addShare(4, "testCase2");
	}
	
	@Test
	public void testJiujingB() throws SQLException {
		//fail("Not yet implemented");
		JiuJingB r=new JiuJingB();
		ResultSet x=r.getJiuJingData();
		int t=0;
		while (x.next()) t++;
		assertEquals(t,8);
		
	}
	@Test
	public void testShareCB() throws SQLException {
		//fail("Not yet implemented");
		ShareCB c=new ShareCB();
		boolean x=c.addShare("testCase", "127", "suning");
		
	}
}

