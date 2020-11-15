package com.versuchdrei.datamanager.datasource.database;

/**
 * a class representing a column and its value in an UPDATE statement
 * @author VersuchDrei
 * @version 1.0
 */
public class UpdateColumnEntry extends ColumnEntry{

	private final boolean key;

	public UpdateColumnEntry(final String column, final ColumnType type, final String value, final boolean key) {
		super(column, type, value);
		
		this.key = key;
	}
	
	public boolean isKey() {
		return this.key;
	}
}
