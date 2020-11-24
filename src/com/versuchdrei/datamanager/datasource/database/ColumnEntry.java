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
	
	public boolean asBoolean() {
		if(this.value.equals("0") || this.value.equals("false") || this.value.equals("")) {
			return false;
		}
		
		return true;
	}
	
	public double asDouble() {
		return Double.parseDouble(this.value);
	}
	
	public float asFloat() {
		return Float.parseFloat(this.value);
	}
	
	public int asInt() {
		return Integer.parseInt(this.value);
	}
	
	public long asLong() {
		return Long.parseLong(this.value);
	}
	
	public String asString() {
		return this.value;
	}

}
