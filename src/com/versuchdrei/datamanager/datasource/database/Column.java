package com.versuchdrei.datamanager.datasource.database;

/**
 * a wrapper class for all properties of a column
 * @author VersuchDrei
 * @version 1.0
 */
public class Column {

	private final String title;
	private final ColumnType type;
	private final boolean unique;
	private final ForeignKey foreignKey;
	
	public Column(final String title, final ColumnType type) {
		this.title = title;
		this.type = type;
		this.unique = false;
		this.foreignKey = null;
	}
	
	public Column(final String title, final ColumnType type, final boolean unique) {
		this.title = title;
		this.type = type;
		this.unique = unique;
		this.foreignKey = null;
	}
	
	public Column(final String title, final ColumnType type, final boolean unique, final ForeignKey foreignKey) {
		this.title = title;
		this.type = type;
		this.unique = unique;
		this.foreignKey = foreignKey;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public ColumnType getType() {
		return this.type;
	}
	
	public boolean isUnique() {
		return this.unique;
	}
	
	public boolean hasForeignKey() {
		return this.foreignKey != null;
	}
	
	public ForeignKey getForeignKey() {
		return this.foreignKey;
	}
}
