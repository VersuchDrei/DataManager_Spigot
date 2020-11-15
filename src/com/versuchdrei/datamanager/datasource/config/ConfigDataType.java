package com.versuchdrei.datamanager.datasource.config;

/**
 * an enum for all keys for config type backends
 * @author VersuchDrei
 * @version 1.0
 */
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
