package com.skitskurr.datamanager.datasource;

public enum ConfigDataType {
	STRING("string"),
	INT("int"),
	LONG("long"),
	DOUBLE("double"),
	FLOAT("float"),
	BOOLEAN("boolean"),
	LIST("list");
	
	private final String configKey;
	
	private ConfigDataType(final String configKey) {
		this.configKey = configKey;
	}
	
	public String getKey() {
		return this.configKey;
	}
}
