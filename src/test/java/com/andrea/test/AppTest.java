package com.andrea.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.andrea.wrapper.SQLiteWrapper;

import junit.framework.TestCase;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	public void testApp() {
		String expectedQuery = "leo 1 yui 2 "; 
		String myQuery = "";

		try {
			SQLiteWrapper.execute("drop table if exists person");
			SQLiteWrapper.execute("create table person (id integer, name string)");
			SQLiteWrapper.execute("insert into person values(1, 'leo')");
			SQLiteWrapper.execute("insert into person values(2, 'yui')");

			ResultSet rs = SQLiteWrapper.extract("select * from person");
			while(rs.next()) {
				// read the result set
				myQuery += (rs.getString("name") + " " + rs.getInt("id") + " ");
			}

			SQLiteWrapper.closeDb();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue( myQuery.equals(expectedQuery) );
	}

	public void testReflection() throws InstantiationException, IllegalAccessException {
		String expectedQuery = "leo 1 yui 2 "; 
		String myQuery = "";

		try {
			SQLiteWrapper.execute("drop table if exists person");
			SQLiteWrapper.execute("create table person (id integer, name string)");
			SQLiteWrapper.execute("insert into person values(1, 'leo')");
			SQLiteWrapper.execute("insert into person values(2, 'yui')");
			
			ArrayList<Map<String, Object>> a = SQLiteWrapper.extractHashMap("select * from person");
			for(Map<String, Object> item : a){
				for (Map.Entry<String, Object> entry : item.entrySet()) {
					System.out.println(entry.getValue() + " ");
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue( myQuery.equals(expectedQuery) );
	}
}
