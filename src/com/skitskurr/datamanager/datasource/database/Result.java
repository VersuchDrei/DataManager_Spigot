package com.skitskurr.datamanager.datasource.database;

import java.util.List;

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
