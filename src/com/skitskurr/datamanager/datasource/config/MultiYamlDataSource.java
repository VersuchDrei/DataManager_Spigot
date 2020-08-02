package com.skitskurr.datamanager.datasource.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.skitskurr.datamanager.datasource.DataSource;

public class MultiYamlDataSource implements DataSource{
	
	private static final String FILE_NAME_GLOBAL = "globalConfig.yml";
	private static final String PLAYER_FILE_PATH = DataSource.FILE_PATH + "players/";
	private static final String GROUP_FILE_PATH = DataSource.FILE_PATH + "groups/";
	private static final String FILE_EXTENSION = ".yml";

	private static final String CONFIG_TYPE_GLOBAL = "global";
	private static final String CONFIG_TYPE_GROUPS = "groups";
	private static final String CONFIG_TYPE_MEMBERS = "members";
	
	private class ConfigPair{
		
		private final File configFile;
		private final YamlConfiguration config;
		
		private ConfigPair(final File configFile, final YamlConfiguration config) {
			this.configFile = configFile;
			this.config = config;
		}
		
		private boolean save() {
			try {
				config.save(configFile);
				return true;
			} catch(final IOException ex) {
				return false;
			}
		}
	}
	
	private class EventListener implements Listener{
		
		@EventHandler
		public void onJoin(final PlayerJoinEvent event) {
			final UUID uuid = event.getPlayer().getUniqueId();
			final File configFile = new File(MultiYamlDataSource.PLAYER_FILE_PATH + uuid.toString() + MultiYamlDataSource.FILE_EXTENSION);
			final YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
			
			MultiYamlDataSource.this.playerConfigs.put(uuid, new ConfigPair(configFile, config));
		}
		
		@EventHandler
		public void onQuit(final PlayerQuitEvent event) {
			final UUID uuid = event.getPlayer().getUniqueId();
			MultiYamlDataSource.this.playerConfigs.get(uuid).save();
			
			MultiYamlDataSource.this.playerConfigs.remove(uuid);
		}
		
	}
	
	private final JavaPlugin plugin;
	
	private final File globalFile;
	private final YamlConfiguration globalConfig;
	private final Map<UUID, ConfigPair> playerConfigs = new HashMap<>();
	private final Map<String, Map<String, ConfigPair>> groupConfigs = new HashMap<>();
	
	public MultiYamlDataSource(final JavaPlugin plugin) {
		this.plugin = plugin;
		this.globalFile = new File(DataSource.FILE_PATH + MultiYamlDataSource.FILE_NAME_GLOBAL);
		this.globalConfig = YamlConfiguration.loadConfiguration(globalFile);
	}
	
	@Override
	public void setup() {
		new File(MultiYamlDataSource.PLAYER_FILE_PATH).mkdirs();
		new File(MultiYamlDataSource.GROUP_FILE_PATH).mkdirs();
		Bukkit.getPluginManager().registerEvents(new EventListener(), plugin);
		setUpGroups();
	}

	@Override
	public void close() {
		saveGlobal();
		for(final ConfigPair pair: this.playerConfigs.values()) {
			pair.save();
		}
		for(final Map<String, ConfigPair> map: this.groupConfigs.values()) {
			for(final ConfigPair pair: map.values()) {
				pair.save();
			}
		}
		
		this.playerConfigs.clear();
		this.groupConfigs.clear();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final String data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.STRING, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final int data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.INT, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final long data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.LONG, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final float data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final double data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final boolean data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final List<String> data) {
		this.globalConfig.set(buildConfigKeyGlobalData(ConfigDataType.LIST, pluginKey, dataKey), data);
		return saveGlobal();
	}

	@Override
	public Optional<String> getString(final String pluginKey, final String dataKey) {
		final String data = this.globalConfig.getString(buildConfigKeyGlobalData(ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.INT, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.globalConfig.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.LONG, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.globalConfig.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) this.globalConfig.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.globalConfig.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.globalConfig.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.LIST, pluginKey, dataKey);
		if(!this.globalConfig.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.globalConfig.getStringList(configKey));
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final String data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.STRING, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final int data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.INT, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final long data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.LONG, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final float data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final double data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final boolean data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final List<String> data) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerData(ConfigDataType.LIST, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public Optional<String> getString(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String data = pair.config.getString(buildConfigKeyPlayerData(ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.INT, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.LONG, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) pair.config.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerData(ConfigDataType.LIST, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getStringList(configKey));
	}
	
	@Override
	public boolean addGroup(final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			new File(MultiYamlDataSource.GROUP_FILE_PATH + group + "/").mkdir();
			this.groupConfigs.put(pluginKey, new HashMap<String, ConfigPair>());
		}
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map.containsKey(group)) {
			return false;
		}
		
		final File configFile = new File(MultiYamlDataSource.GROUP_FILE_PATH + group + MultiYamlDataSource.FILE_EXTENSION);
		final YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		final ConfigPair pair = new ConfigPair(configFile, config);
		if(!pair.save()) {
			return false;
		}
		
		map.put(group, pair);
		return true;
	}
	
	@Override
	public boolean deleteGroup(final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return false;
		}
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(!map.containsKey(group)) {
			return false;
		}
		
		final ConfigPair groupPair = map.get(group);
		final List<String> uuids = groupPair.config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS);
		for(final String uuid: uuids) {
			final ConfigPair playerPair = this.playerConfigs.get(UUID.fromString(uuid));
			final String configKey = buildConfigKeyPlayerGroup(group, pluginKey);
			playerPair.config.set(configKey, null);
		}
		
		map.remove(group);
		groupPair.configFile.delete();
		return true;
	}
	
	@Override
	public boolean isGroup(final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return false;
		}
		
		return this.groupConfigs.get(pluginKey).containsKey(group);
	}
	
	@Override
	public boolean addMember(final OfflinePlayer player, final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return false;
		}
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(!map.containsKey(group)) {
			return false;
		}

		final String uuid = player.getUniqueId().toString();
		final ConfigPair pair = map.get(group);
		final List<String> uuids = pair.config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS);
		if(uuids.contains(uuid)) {
			return false;
		}
		uuids.add(uuid);
		pair.config.set(MultiYamlDataSource.CONFIG_TYPE_MEMBERS, uuids);
		return pair.save();
	}
	
	@Override
	public boolean removeMember(final OfflinePlayer player, final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return false;
		}
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(!map.containsKey(group)) {
			return false;
		}

		final String uuid = player.getUniqueId().toString();
		final ConfigPair pair = map.get(group);
		final List<String> uuids = pair.config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS);
		if(!uuids.contains(uuid)) {
			return false;
		}
		uuids.remove(uuid);
		pair.config.set(MultiYamlDataSource.CONFIG_TYPE_MEMBERS, uuids);
		return pair.save();
	}
	
	@Override
	public boolean isMember(final OfflinePlayer player, final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return false;
		}
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(!map.containsKey(group)) {
			return false;
		}

		final String uuid = player.getUniqueId().toString();
		final ConfigPair pair = map.get(group);
		final List<String> uuids = pair.config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS);
		
		return uuids.contains(uuid);
	}
	
	@Override
	public Optional<List<UUID>> getMemberIDs(final String group, final String pluginKey){
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return Optional.empty();
		}
		
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(!map.containsKey(group)) {
			return Optional.empty();
		}
		
		final ConfigPair pair = map.get(group);
		final List<UUID> members = pair.config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS)
				.stream().map(uuid -> UUID.fromString(uuid)).collect(Collectors.toList());
		
		return Optional.of(members);
	}
	
	@Override
	public List<String> getGroups(final String pluginKey){
		return this.groupConfigs.get(pluginKey).keySet().stream().collect(Collectors.toList());
	}
	
	@Override
	public List<String> getGroups(final OfflinePlayer player, final String pluginKey){
		return this.groupConfigs.get(pluginKey).entrySet().stream()
				.filter(entry -> entry.getValue().config.getStringList(MultiYamlDataSource.CONFIG_TYPE_MEMBERS).contains(player.getUniqueId().toString()))
				.map(entry -> entry.getKey()).collect(Collectors.toList());
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final String data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.STRING, dataKey), data);
		return pair.save();		
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final int data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.INT, dataKey), data);
		return pair.save();	
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final long data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.LONG, dataKey), data);
		return pair.save();	
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final float data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.FLOAT, dataKey), data);
		return pair.save();	
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final double data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.DOUBLE, dataKey), data);
		return pair.save();	
	}
	
	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final boolean data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.BOOLEAN, dataKey), data);
		return pair.save();	
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final List<String> data) {
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return false;
		}
		pair.config.set(buildConfigKeyGroupData(ConfigDataType.LIST, dataKey), data);
		return pair.save();	
	}
	
	@Override
	public Optional<String> getString(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String data = pair.config.getString(buildConfigKeyGroupData(ConfigDataType.STRING, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}
	
	@Override
	public Optional<Integer> getInt(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.INT, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of(pair.config.getInt(configKey));
	}
	
	@Override
	public Optional<Long> getLong(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.LONG, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of(pair.config.getLong(configKey));
	}
	
	@Override
	public Optional<Float> getFloat(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.FLOAT, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of((float) pair.config.getDouble(configKey));
	}
	
	@Override
	public Optional<Double> getDouble(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.DOUBLE, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of(pair.config.getDouble(configKey));
	}
	
	@Override
	public Optional<Boolean> getBoolean(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.BOOLEAN, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of(pair.config.getBoolean(configKey));
	}
	
	@Override
	public Optional<List<String>> getList(final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = getGroupPair(group, pluginKey);
		if(pair == null) {
			return Optional.empty();
		}
		
		final String configKey = buildConfigKeyGroupData(ConfigDataType.LIST, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		
		return Optional.of(pair.config.getStringList(configKey));
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final String data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.STRING, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final int data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.INT, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final long data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.LONG, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final float data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final double data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final boolean data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return pair.save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final List<String> data) {
		final Map<String, ConfigPair> map = this.groupConfigs.get(pluginKey);
		if(map == null || !map.containsKey(group)) {
			return false;
		}
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		pair.config.set(buildConfigKeyPlayerGroupData(group, ConfigDataType.LIST, pluginKey, dataKey), data);
		return pair.save();
	}
	
	@Override
	public Optional<String> getString(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String data = pair.config.getString(buildConfigKeyPlayerGroupData(group, ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}
	
	@Override
	public Optional<Integer> getInt(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.INT, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getInt(configKey));
	}
	
	@Override
	public Optional<Long> getLong(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.LONG, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getLong(configKey));
	}
	
	@Override
	public Optional<Float> getFloat(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) pair.config.getDouble(configKey));
	}
	
	@Override
	public Optional<Double> getDouble(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getDouble(configKey));
	}
	
	@Override
	public Optional<Boolean> getBoolean(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getBoolean(configKey));
	}
	
	@Override
	public Optional<List<String>> getList(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
		final ConfigPair pair = this.playerConfigs.get(player.getUniqueId());
		final String configKey = buildConfigKeyPlayerGroupData(group, ConfigDataType.LIST, pluginKey, dataKey);
		if(!pair.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(pair.config.getStringList(configKey));
	}
	
	private static String buildConfigKeyGlobalData(final ConfigDataType type, final String pluginKey, final String dataKey) {
		return MultiYamlDataSource.CONFIG_TYPE_GLOBAL + "." + pluginKey + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerData(final ConfigDataType type, final String pluginKey, final String dataKey) {
		return pluginKey + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyGroupData(final ConfigDataType type, final String dataKey) {
		return type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerGroup(final String group, final String pluginKey) {
		return pluginKey + "." + MultiYamlDataSource.CONFIG_TYPE_GROUPS + "." + group;
	}
	
	private static String buildConfigKeyPlayerGroupData(final String group, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return buildConfigKeyPlayerGroup(group, pluginKey) + "." + type.getKey() + "." + dataKey;
	}
	
	private void setUpGroups() {
		try (Stream<Path> walk = Files.walk(Paths.get(MultiYamlDataSource.GROUP_FILE_PATH))) {

			final List<String> result = walk.filter(Files::isDirectory)
					.map(x -> x.getFileName().toString()).collect(Collectors.toList());

			result.forEach(this::setUpPluginGroups);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpPluginGroups(final String pluginKey) {
		this.groupConfigs.put(pluginKey, new HashMap<String, ConfigPair>());
		
		try (Stream<Path> walk = Files.walk(Paths.get(MultiYamlDataSource.GROUP_FILE_PATH + pluginKey + "/"))) {

			final List<File> result = walk.filter(path -> path.toString().endsWith(MultiYamlDataSource.FILE_EXTENSION))
					.map(path -> path.toFile()).collect(Collectors.toList());

			result.forEach(file -> registerGroup(pluginKey, file));

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerGroup(final String pluginKey, final File file) {
		final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		// regex for cutting off the file extension
		this.groupConfigs.get(pluginKey).put(file.getName().replaceFirst("[.][^.]+$", ""), new ConfigPair(file, config));
	}
	
	private ConfigPair getGroupPair(final String group, final String pluginKey) {
		if(!this.groupConfigs.containsKey(pluginKey)) {
			return null;
		}
		return this.groupConfigs.get(pluginKey).get(group);
	}
	

	
	private boolean saveGlobal() {
		try {
			this.globalConfig.save(this.globalFile);
			return true;
		}catch(final IOException ex) {
			return false;
		}
	}

}
