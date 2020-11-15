package com.versuchdrei.datamanager.datasource.database;

/**
 * a class representing a column and its value in an INSERT statement
 * @author VersuchDrei
 * @version 1.0
 */
public class ColumnEntry {
	
	private final String column;
	private final ColumnType type;
	private final String value;
	
	public ColumnEntry(final String column, final ColumnType type, final String value) {
		this.column = column;
		this.type = type;
		this.value = value;
	}
	
	public String getColumn() {
		return this.column;
	}
	
	public ColumnType getType() {
		return this.type;
	}
	
	public String getValue() {
		return this.value;
	}

}
