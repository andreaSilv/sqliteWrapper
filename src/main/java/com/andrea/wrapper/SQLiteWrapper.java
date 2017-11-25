package com.andrea.wrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.andrea.util.Util;
import com.google.common.collect.Maps;

public class SQLiteWrapper {
	public static String driverClassName = "org.sqlite.JDBC";
	public static String driverConnectionName = "jdbc:sqlite:sample.db";
	public static int queryTimeOut = 30;
	
	public static Connection connection = null;
	public static Statement statement = null;

	public static ArrayList<Map<String,Object>> extractHashMap(String query) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = extract(query);
		ArrayList<Map<String,Object>> entries = new ArrayList<Map<String,Object>>();
		
		while(resultSet.next()) {
			Map<String,Object> record = Util.convertResultsetToMap(resultSet);
			entries.add(record);
		}
		return entries;
	}
	
	public static ResultSet extract(String query) throws ClassNotFoundException, SQLException {

		if ( isConnectionOpened() ) {
			connection = createConnection();
		}
		if ( isStatementOpened() )  {	
			statement = createStatement(connection);
		}
		ResultSet resultOfQuery = statement.executeQuery(query);
		return resultOfQuery;
	}

	public static void execute(String sqlCode) throws ClassNotFoundException, SQLException {

		if ( isConnectionOpened() ) {
			connection = createConnection();
		}
		if ( isStatementOpened() )  {	
			statement = createStatement(connection);
		}
		statement.executeUpdate(sqlCode);
	}

	public static void closeDb() throws SQLException {
		statement.close();
		statement = null;

		connection.close();
		connection = null;
	}

	//---------------START-UTILS	
	private static boolean isConnectionOpened() {
		return connection == null;
	}

	private static boolean isStatementOpened() {
		return statement == null;
	}

	private static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driverClassName);
		return DriverManager.getConnection(driverConnectionName);	
	}

	private static Statement  createStatement(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(queryTimeOut);  // set timeout to 30 sec.
		return statement;
	}
	//----------------END-UTILS
}

