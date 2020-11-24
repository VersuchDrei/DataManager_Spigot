package com.versuchdrei.datamanager.datasource.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * an implementation of a database backend using MySQL
 * @author VersuchDrei
 * @version 1.0
 */
public class MySQLDataSource extends DBDataSource{

	private final String hostname;
	private final String port;
	private final String database;
	private final String user;
	private final String password;
	
	private Connection connection;

	public MySQLDataSource(final String hostname, final String port, final String database, final String user, final String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	private Connection getOpenConnection() throws SQLException {
		if(this.connection != null && !this.connection.isClosed()) {
			return this.connection;
		}
			
		this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
		return this.connection;
	}
	
	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createTable(final String name, final List<Column> columns) {
		final String sqlColumns = columns.stream().map(column -> formatColumn(column)).collect(Collectors.joining(", "));
		final String primaryKeys = columns.stream().filter(column -> column.isUnique()).map(column -> column.getTitle()).collect(Collectors.joining(", "));
		final String foreignKeys = columns.stream().filter(column -> column.hasForeignKey()).map(column -> ", " + formatForeignKey(column)).collect(Collectors.joining());
		
		final String sql = "CREATE TABLE IF NOT EXISTS " + name + " (" + sqlColumns + ", PRIMARY KEY (" + primaryKeys + ")" + foreignKeys + ");";
		try (Statement statement = getOpenConnection().createStatement();){
			statement.executeUpdate(sql);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected boolean updateValue(final String table, final List<UpdateColumnEntry> columnEntries) {
		String columns = columnEntries.get(0).getColumn();
		String values = "?";
		
		final Iterator<UpdateColumnEntry> iterator = columnEntries.iterator();
		iterator.next();
		while(iterator.hasNext()) {
			final UpdateColumnEntry entry = iterator.next();
			columns += ", " + entry.getColumn();
			values += ", ?";
		}
		
		final String sql = "REPLACE INTO " + table + " (" + columns + ") VALUES (" + values + ")";
		try (PreparedStatement statement = getOpenConnection().prepareStatement(sql)){
			addSQLparameters(statement, columnEntries);
			statement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	protected Result getResult(final String table, final String column, final List<ColumnEntry> keys) {
		final String where = keys.stream().map(key -> formatWhere(key)).collect(Collectors.joining(" AND "));
		
		final String sql = "SELECT " + column + " FROM " +  table + " WHERE " +  where;
		try (PreparedStatement statement = getOpenConnection().prepareStatement(sql)){
			addSQLparameters(statement, keys);
			return new MySQLResult(statement.executeQuery(), column);
		} catch (final SQLException e) {
			e.printStackTrace();
			return new EmptyResult();
		}
	}

	@Override
	protected boolean deleteValue(final String table, final List<ColumnEntry> keys) {
		final String where = keys.stream().map(key -> formatWhere(key)).collect(Collectors.joining(" AND "));
		
		final String sql = "DELETE FROM " + table + " WHERE " + where;
		try (PreparedStatement statement = getOpenConnection().prepareStatement(sql)){
			addSQLparameters(statement, keys);
			statement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	protected boolean exists(final String table, final List<ColumnEntry> keys) {
		final String where = keys.stream().map(key -> formatWhere(key)).collect(Collectors.joining(" AND "));
		
		final String sql = "SELECT " + keys.get(0).getColumn() + " FROM " + table + " WHERE " + where;
		try (PreparedStatement statement = getOpenConnection().prepareStatement(sql)){
			addSQLparameters(statement, keys);
			final ResultSet result = statement.executeQuery();
			return result.next();
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String formatColumn(final Column column) {
		String formatted = column.getTitle();
		switch(column.getType()) {
		case INT:
			formatted += " INT(10)";
			break;
		case LONG:
			formatted += " INT(19)";
			break;
		case FLOAT:
			formatted += " FLOAT";
		case DOUBLE:
			formatted += " DOUBLE";
			break;
		case BOOLEAN:
			formatted += " SMALLINT(1)";
			break;
		case STRING_KEY:
			formatted += " VARCHAR(36)";
			break;
		case STRING_VALUE:
		case STRING_LIST:
			formatted += " VARCHAR(max)";
			break;
		default:
			formatted += " VARCHAR(max)";
		}
		
		if(column.isUnique()) {
			formatted += " NOT NULL";
		} else {
			formatted += " NULL";
		}
		
		return formatted;
	}
	
	private String formatForeignKey(final Column column) {
		if(!column.hasForeignKey()) {
			throw new IllegalArgumentException("Column has no foreign key.");
		}
		final ForeignKey key = column.getForeignKey();
		return "FOREIGN KEY (" + column.getTitle() + ") REFERENCES " + key.getTable() + "(" + key.getColumn() + ") ON DELETE CASCADE";
	}
	
	private void addSQLparameters(final PreparedStatement statement, final List<? extends ColumnEntry> columnEntries) throws SQLException {
		int j = 1;
		for(final ColumnEntry columnEntry: columnEntries) {
			switch(columnEntry.getType()) {
			case BOOLEAN:
				statement.setBoolean(j++, columnEntry.asBoolean());
				break;
			case DOUBLE:
				statement.setDouble(j++, columnEntry.asDouble());
				break;
			case FLOAT:
				statement.setFloat(j++, columnEntry.asFloat());
				break;
			case INT:
				statement.setInt(j++, columnEntry.asInt());
				break;
			case LONG:
				statement.setLong(j++, columnEntry.asLong());
				break;
			case STRING_KEY:
				statement.setString(j++, columnEntry.asString());
				break;
			case STRING_LIST:
				statement.setString(j++, columnEntry.asString());
				break;
			case STRING_VALUE:
				statement.setString(j++, columnEntry.asString());
				break;
			default:
				break;
			}
		}
	}
	
	private String formatWhere(final ColumnEntry entry) {
		switch(entry.getType()) {
		case INT:
		case LONG:
		case FLOAT:
		case DOUBLE:
		case BOOLEAN:
			return entry.getColumn() + " = ?";
		case STRING_KEY:
		case STRING_VALUE:
		case STRING_LIST:
			return entry.getColumn() + " LIKE ?";
		default:
			return entry.getColumn() + " = ?";
		}
	}

}
