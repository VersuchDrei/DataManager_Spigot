package com.skitskurr.datamanager.datasource.database;

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
