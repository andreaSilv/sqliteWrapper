package com.andrea.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.andrea.util.AbstactSql;
import com.andrea.util.Util;
import com.andrea.wrapper.SQLiteWrapper;
import com.google.common.base.Joiner;

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
		String expectedResult = "leo 1 yui 2 "; 
		String myResult = "";

		try {
			testExecuteQuery();
						
			ResultSet rs = SQLiteWrapper.extract("select * from person");
			while(rs.next()) {
				// read the result set
				myResult += (rs.getString("name") + " " + rs.getInt("id") + " ");
			}

			SQLiteWrapper.closeDb();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue( myResult.equals(expectedResult) );
	}
	
	public void testHashMapQuery() throws InstantiationException, IllegalAccessException {
		String expectedResult = "leo 1 yui 2 "; 
		String myResult = "";

		try {
			testExecuteQuery();
			
			ArrayList<Map<String, Object>> a = SQLiteWrapper.extractHashMap("select * from person");
			for(Map<String, Object> item : a){
				for (Map.Entry<String, Object> entry : item.entrySet()) {
					myResult += entry.getValue() + " ";
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue( myResult.equals(expectedResult) );
	}
	
	public void testConvertMethodObjectToMap() {
		
		String expectedResult = "a 1 b cacca c 3"; 
		String myResult = "";
		
		class Sub {
			public int a = 1;
			public String b = "cacca";
			public int c = 3;
		}
		
		Sub s = new Sub();
		
		Map<String, Object> mapOfSubClass = null;
		try {
			mapOfSubClass = Util.convertObjectToMap(s);
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		myResult = Joiner.on(" ").withKeyValueSeparator(" ").join(mapOfSubClass);
		try {
			AbstactSql.insert(s);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myResult + "|" + expectedResult);
		assertTrue(myResult.equals(expectedResult));
	}
}
