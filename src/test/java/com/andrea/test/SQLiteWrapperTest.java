package com.andrea.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.andrea.wrapper.SQLiteWrapper;

import junit.framework.TestCase;

public class SQLiteWrapperTest extends TestCase{
	
	public void testExecuteQuery() {
		try {
			SQLiteWrapper.execute("drop table if exists person");
			SQLiteWrapper.execute("create table person (id integer, name string)");
			SQLiteWrapper.execute("insert into person values(1, 'leo')");
			SQLiteWrapper.execute("insert into person values(2, 'yui')");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void testResultSetQuery() {
		String expectedQuery = "leo 1 yui 2 "; 
		String myQuery = "";

		try {
			testExecuteQuery();
						
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
	
	public void testHashMapQuery() throws InstantiationException, IllegalAccessException {
		String expectedQuery = "leo 1 yui 2 "; 
		String myQuery = "";

		try {
			testExecuteQuery();
			
			ArrayList<Map<String, Object>> a = SQLiteWrapper.extractHashMap("select * from person");
			for(Map<String, Object> item : a){
				for (Map.Entry<String, Object> entry : item.entrySet()) {
					myQuery += entry.getValue() + " ";
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
