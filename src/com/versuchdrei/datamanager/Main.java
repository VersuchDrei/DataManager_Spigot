package com.versuchdrei.datamanager;

import java.util.Optional;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.versuchdrei.datamanager.datasource.DataSource;
import com.versuchdrei.datamanager.datasource.config.MultiYamlDataSource;
import com.versuchdrei.datamanager.datasource.config.SingleYamlDataSource;
import com.versuchdrei.datamanager.datasource.database.MySQLDataSource;

/**
 * the main class of the data manager
 * @author VersuchDrei
 * @version 1.0
 */
public class Main extends JavaPlugin{
	
	private static final String CONFIG_KEY_DATA_SYSTEM = "dataSystem";
	private static final String CONFIG_KEY_HOSTNAME = "hostname";
	private static final String CONFIG_KEY_PORT = "port";
	private static final String CONFIG_KEY_DATABASE = "database";
	private static final String CONFIG_KEY_USER = "user";
	private static final String CONFIG_KEY_PASSWORD = "password";
	
	private static Main current;
	
	/**
	 * returns the current instance of the main plugin
	 * @return the instance of the main plugin
	 */
	public static Optional<Main> getCurrent(){
		if(current == null) {
			return Optional.empty();
		}
		return Optional.of(current);
	}
	
	private DataSource dataSource;
	
	@Override
	public void onEnable() {
		super.saveDefaultConfig();
		final FileConfiguration config = getConfig();
		final DataSystem dataSystem = DataSystem.valueOf(config.getString(Main.CONFIG_KEY_DATA_SYSTEM).toUpperCase());
		switch(dataSystem) {
		case SINGLE_YAML:
			this.dataSource = new SingleYamlDataSource();
			break;
		case MULTI_YAML:
			this.dataSource = new MultiYamlDataSource(this);
			break;
		case MYSQL:
			final String hostname = config.getString(Main.CONFIG_KEY_HOSTNAME);
			final String port = config.getString(Main.CONFIG_KEY_PORT);
			final String database = config.getString(Main.CONFIG_KEY_DATABASE);
			final String user = config.getString(Main.CONFIG_KEY_USER);
			final String password = config.getString(Main.CONFIG_KEY_PASSWORD);
			this.dataSource = new MySQLDataSource(hostname, port, database, user, password);
			break;
		}
		this.dataSource.setup();
		Main.current = this;
	}
	
	@Override
	public void onDisable() {
		Main.current = null;
		this.dataSource.close();
		this.dataSource = null;
		// with the support of MySQL we need to close the connection here
	}
	
	Optional<DataSource> getDataSource(){
		if(dataSource == null) {
			return Optional.empty();
		}
		
		return Optional.of(dataSource);
	}

}
