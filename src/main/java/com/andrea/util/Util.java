package com.andrea.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import com.google.common.collect.Maps;

public class Util {
	public static Map<String, Object> convertResultsetToMap(ResultSet resultSet) throws SQLException{
		Map<String, Object> record = Maps.newHashMap();
		
		ResultSetMetaData metadata = resultSet.getMetaData();
		int columnCount = metadata.getColumnCount();    
		for (int i = 1; i <= columnCount; i++) {
			
			String columnName = metadata.getColumnName(i);
			Object value = resultSet.getObject(columnName);
			
			record.put(columnName, value);      
		}
		return record;
	}
}
