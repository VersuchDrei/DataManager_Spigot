package com.skitskurr.datamanager.datasource.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skitskurr.datamanager.utils.StringUtils;

public class MySQLResult implements Result{

	private final ResultSet resultSet;
	private final String column;
	private final boolean empty;
	
	public MySQLResult(final ResultSet resultSet, final String column) {
		this.resultSet = resultSet;
		this.column = column;
		boolean innerEmpty = true;
		try {
			innerEmpty = !resultSet.next();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		this.empty = innerEmpty;
	}

	@Override
	public boolean isEmpty() {
		return this.empty;
	}

	@Override
	public String getString() {
		try {
			return this.resultSet.getString(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public int getInt() {
		try {
			return this.resultSet.getInt(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public long getLong() {
		try {
			return this.resultSet.getLong(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public float getFloat() {
		try {
			return this.resultSet.getFloat(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double getDouble() {
		try {
			return this.resultSet.getDouble(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean getBoolean() {
		try {
			return this.resultSet.getBoolean(column);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> getList() {
		try {
			return StringUtils.stringToList(this.resultSet.getString(column));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
}
