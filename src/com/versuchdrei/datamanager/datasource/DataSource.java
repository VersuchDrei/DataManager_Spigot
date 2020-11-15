package com.versuchdrei.datamanager.datasource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * the super interface for all backend systems
 * @author VersuchDrei
 * @version 1.0
 */
public interface DataSource {
	
	static final String FILE_PATH = "plugins/DataManager/";
	
	public void setup();
	
	public void close();

	public boolean set(String pluginKey, String dataKey, String data) ;
	
	public boolean set(String pluginKey, String dataKey, int data);
	
	public boolean set(String pluginKey, String dataKey, long data);
	
	public boolean set(String pluginKey, String dataKey, float data);
	
	public boolean set(String pluginKey, String dataKey, double data);
	
	public boolean set(String pluginKey, String dataKey, boolean data);

	public boolean set(String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(String pluginKey, String dataKey);
	
	public Optional<Long> getLong(String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(String pluginKey, String dataKey);

	public boolean set(UUID uuid, String pluginKey, String dataKey, String data) ;
	
	public boolean set(UUID uuid, String pluginKey, String dataKey, int data);
	
	public boolean set(UUID uuid, String pluginKey, String dataKey, long data);
	
	public boolean set(UUID uuid, String pluginKey, String dataKey, float data);
	
	public boolean set(UUID uuid, String pluginKey, String dataKey, double data);
	
	public boolean set(UUID uuid, String pluginKey, String dataKey, boolean data);

	public boolean set(UUID uuid, String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<Long> getLong(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(UUID uuid, String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(UUID uuid, String pluginKey, String dataKey);
	
	public boolean addGroup(String group, String pluginKey);
	
	public boolean deleteGroup(String group, String pluginKey);
	
	public boolean isGroup(String group, String pluginKey);
	
	public boolean addMember(UUID uuid, String group, String pluginKey);
	
	public boolean removeMember(UUID uuid, String group, String pluginKey);
	
	public boolean isMember(UUID uuid, String group, String pluginKey);
	
	public Optional<List<UUID>> getMemberIDs(String group, String pluginKey);
	
	public List<String> getGroups(String pluginKey);
	
	public List<String> getGroups(UUID uuid, String pluginKey);
	
	public boolean set(String group, String pluginKey, String dataKey, String data);
	
	public boolean set(String group, String pluginKey, String dataKey, int data);
	
	public boolean set(String group, String pluginKey, String dataKey, long data);
	
	public boolean set(String group, String pluginKey, String dataKey, float data);
	
	public boolean set(String group, String pluginKey, String dataKey, double data);
	
	public boolean set(String group, String pluginKey, String dataKey, boolean data);

	public boolean set(String group, String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(String group, String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(String group, String pluginKey, String dataKey);
	
	public Optional<Long> getLong(String group, String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(String group, String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(String group, String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(String group, String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(String group, String pluginKey, String dataKey);

	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, String data) ;
	
	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, int data);
	
	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, long data);
	
	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, float data);
	
	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, double data);
	
	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, boolean data);

	public boolean set(UUID uuid, String group, String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<Long> getLong(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(UUID uuid, String group, String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(UUID uuid, String group, String pluginKey, String dataKey);

}
