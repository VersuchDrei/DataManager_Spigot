package com.skitskurr.datamanager.datasource.database;

import java.util.List;

public class EmptyResult implements Result{

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String getString() {
		return null;
	}

	@Override
	public int getInt() {
		return 0;
	}

	@Override
	public long getLong() {
		return 0;
	}

	@Override
	public float getFloat() {
		return 0;
	}

	@Override
	public double getDouble() {
		return 0;
	}

	@Override
	public boolean getBoolean() {
		return false;
	}

	@Override
	public List<String> getList() {
		return null;
	}

}
