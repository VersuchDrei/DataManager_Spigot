package com.versuchdrei.datamanager.datasource.database;

/**
 * The different types of data to hold. 
 * Whether or not some of these will use the same type on the database is up to the implementation of the DataSource.
 * @author VersuchDrei
 * @version 1.0
 */
public enum ColumnType {
	STRING_KEY, // for string columns used in unique keys, needs to hold 36 characters
	STRING_VALUE, // for string columns used for data values, needs to hold generic strings
	INT,
	LONG,
	FLOAT,
	DOUBLE,
	BOOLEAN, // needs to hold values of 1 and 0
	STRING_LIST; // for string columns used for lists, needs to hold a formatted list
}
