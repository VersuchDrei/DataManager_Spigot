package com.versuchdrei.datamanager.datasource.database;

import java.util.List;

/**
 * an interface for all results of SELECT statements
 * @author VersuchDrei
 * @version 1.0
 */
public interface Result {
	
	boolean isEmpty();
	
	String getString();
	
	int getInt();
	
	long getLong();
	
	float getFloat();
	
	double getDouble();
	
	boolean getBoolean();
	
	List<String> getList();

}
