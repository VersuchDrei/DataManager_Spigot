package com.skitskurr.datamanager.datasource.database;

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
