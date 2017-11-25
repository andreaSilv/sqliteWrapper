package com.andrea.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import com.google.common.collect.Maps;

public class Util {
	
	private final static String CHARACTER_THAT_MEAN_OBJECTSELF = "$";
	
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
	
	public static Map<String, Object> convertObjectToMap(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, Object> objectField = Maps.newHashMap();
		
		Class <?> c = obj.getClass();
		Field[] attributes = c.getDeclaredFields();
		int dim = attributes.length;

		
		for (int i = 0 ; i < dim ; i++) {
			
			String name = attributes[i].getName();
			if (!name.contains(CHARACTER_THAT_MEAN_OBJECTSELF)) {
				Field field = c.getField(name);
				field.setAccessible(true);
				Object value = field.get(obj);
				
				objectField.put(name, value);
			}
		}
		
		return objectField;
	}
}
