package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;


import manage.DataManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastruct.Data;



public class TestDataManager {
	DataManager x;
	@Before
	public void setUp() throws Exception {
		x = new DataManager();
	}

	@After
	public void tearDown() throws Exception {
		x = null;
	}

	@Test
	public void testGetDataByTypeRight() {
		try {
			ResultSet a = x.getDataByType("Info");
			a.next();
			System.out.println(a.getString("COMMENTID"));
			//assertEquals(a.getString("COMMENTID"),"90");
			a = x.getDataByType("Renren");
			a.next();
			System.out.println(a.getString("TITLE"));
			a = x.getDataByType("Jiujing");
			a.next();
			System.out.println(a.getString("WHEN"));
			a = x.getDataByType("Jiu");
			assertEquals(a.next(),false);
			//System.out.println(a.getString("WHEN"));
			//assertEquals(x.getDataByType("Renren").getString("TITLE"),"21");
			//assertEquals(x.getDataByType("Jiujing").getString("WHEN"),"2013-11-15");
			//x.getDataByType("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testHaveTitle() {
		try {
			String title = "";
			for(int i = 0;i < 5;i++)
			{
				System.out.println(x.haveTitle(title));
				title = title + "a";
			}
			assertEquals(x.haveTitle("aaa"),true);
			assertEquals(x.haveTitle("21"),true);
			assertEquals(x.haveTitle("jjjjjj"),true);
			assertEquals(x.haveTitle("34"),true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	public void testGetComData() {
		try {
				ResultSet a = x.getComData("140");
				a.next();
				System.out.println(a.getString("CONTEXT"));
				a = x.getComData("142");
				a.next();
				System.out.println(a.getString("CONTEXT"));
				a = x.getComData("144");
				a.next();
				System.out.println(a.getString("CONTEXT"));
				a = x.getComData("146");
				a.next();
				System.out.println(a.getString("CONTEXT"));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	public void testGetData() {
		try {
			ResultSet a = x.getData("1");
			a.next();
			a.next();
			System.out.println(a.getString("TITLE"));
			a = x.getData("23");
			a.next();
			System.out.println(a.getString("TITLE"));
			a = x.getData("90");
			a.next();
			a.next();
			System.out.println(a.getString("TITLE"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	public void testInsertCommentHave() {
		try {
			
			x.insertComment("a", "2011.1.1", "a", "90", "a", "a");
			x.insertComment("123", "2011-1-1 00:00", "123", "123", "123", "123");
			ResultSet a = x.getData("123");
			a.next();
			System.out.println(a.getString("CONTEXT"));
			assertEquals(a.getString("LINK"),"");
			assertEquals(a.getString("TITLE"),"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	public void testDeleteCommentNone() throws SQLException {
			//x.deleteComment("10000");
			x.deleteComment("252");
	}
	@Test
	public void testAddData() {
		try {
			Data data = new Data();
			x.addData(data);
			data.setCommentTime("a");
			String Id = "";
			while(x.getData(Id).next())
				Id += "b";
			data.setId(Id);
			x.addData(data);
			ResultSet a = x.getData(Id);
			a.next();
			assertEquals(a.getString("ID"),Id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	public void testAddContent() {
		try {
			String link = "";
			x.addContent("a", "111111", "a", "a");
			x.addContent("a", "", "a", "a");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}