package com.skitskurr.datamanager.datasource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

public interface DataSource {
	
	static final String FILE_PATH = "plugins/DataManager/";
	
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

	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, String data) ;
	
	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, int data);
	
	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, long data);
	
	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, float data);
	
	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, double data);
	
	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, boolean data);

	public boolean set(OfflinePlayer player, String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<Long> getLong(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(OfflinePlayer player, String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(OfflinePlayer player, String pluginKey, String dataKey);
	
	public boolean addGroup(String group, String pluginKey);
	
	public boolean deleteGroup(String group, String pluginKey);
	
	public boolean isGroup(String group, String pluginKey);
	
	public boolean addMember(OfflinePlayer player, String group, String pluginKey);
	
	public boolean removeMember(OfflinePlayer player, String group, String pluginKey);
	
	public boolean isMember(OfflinePlayer player, String group, String pluginKey);
	
	public Optional<List<OfflinePlayer>> getMembers(String group, String pluginKey);
	
	public Optional<List<UUID>> getMemberIDs(String group, String pluginKey);
	
	public List<String> getGroups(String pluginKey);
	
	public List<String> getGroups(OfflinePlayer player, String pluginKey);
	
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

	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, String data) ;
	
	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, int data);
	
	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, long data);
	
	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, float data);
	
	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, double data);
	
	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, boolean data);

	public boolean set(OfflinePlayer player, String group, String pluginKey, String dataKey, List<String> data);
	
	public Optional<String> getString(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<Integer> getInt(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<Long> getLong(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<Float> getFloat(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<Double> getDouble(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<Boolean> getBoolean(OfflinePlayer player, String group, String pluginKey, String dataKey);
	
	public Optional<List<String>> getList(OfflinePlayer player, String group, String pluginKey, String dataKey);

}
