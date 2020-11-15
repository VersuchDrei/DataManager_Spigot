package com.versuchdrei.datamanager.datasource.database;

/**
 * a wrapper class for all properties of a foreign key
 * @author VersuchDrei
 * @version 1.0
 */
public class ForeignKey {
	
	private final String table;
	private final String column;
	
	public ForeignKey(final String table, final String column) {
		this.table = table;
		this.column = column;
	}
	
	public String getTable() {
		return this.table;
	}
	
	public String getColumn() {
		return this.column;
	}

}
