package com.skitskurr.datamanager;

import java.util.Optional;

import org.bukkit.plugin.java.JavaPlugin;

import com.skitskurr.datamanager.datasource.DataSource;
import com.skitskurr.datamanager.datasource.MultiYamlDataSource;
import com.skitskurr.datamanager.datasource.SingleYamlDataSource;

public class Main extends JavaPlugin{
	
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
		final DataSystem dataSystem = DataSystem.valueOf(getConfig().getString("dataSystem").toUpperCase());
		switch(dataSystem) {
		case SINGLE_YAML:
			this.dataSource = new SingleYamlDataSource();
			break;
		case MULTI_YAML:
			this.dataSource = new MultiYamlDataSource(this);
			break;
		}
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
