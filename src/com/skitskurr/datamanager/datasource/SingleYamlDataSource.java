package com.skitskurr.datamanager.datasource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class SingleYamlDataSource implements DataSource{
	
	private static final String FILE_NAME = "singleConfig.yml";
	
	private static final String CONFIG_TYPE_GLOBAL = "global";
	private static final String CONFIG_TYPE_PLAYERS = "players";
	private static final String CONFIG_TYPE_GROUPS = "groups";
	private static final String CONFIG_TYPE_MEMBERS = "members";
	
	private final File configFile;
	private final YamlConfiguration config;
	
	public SingleYamlDataSource() {
		this.configFile = new File(DataSource.FILE_PATH + SingleYamlDataSource.FILE_NAME);
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}

	@Override
	public void close() {
		save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final String data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.STRING, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final int data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.INT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final long data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.LONG, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final float data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final double data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final boolean data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final List<String> data) {
		this.config.set(buildConfigKeyGlobalData(ConfigDataType.LIST, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public Optional<String> getString(final String pluginKey, final String dataKey) {
		final String data = this.config.getString(buildConfigKeyGlobalData(ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.INT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.LONG, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) this.config.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGlobalData(ConfigDataType.LIST, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getStringList(configKey));
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final String data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.STRING, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final int data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.INT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final long data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.LONG, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final float data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final double data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final boolean data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final List<String> data) {
		this.config.set(buildConfigKeyPlayerData(player, ConfigDataType.LIST, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public Optional<String> getString(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String data = this.config.getString(buildConfigKeyPlayerData(player, ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.INT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.LONG, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) this.config.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final OfflinePlayer player, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerData(player, ConfigDataType.LIST, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getStringList(configKey));
	}
	
	@Override
	public boolean addGroup(final String group, final String pluginKey) {
		if(isGroup(group, pluginKey)) {
			return false;
		}
		
		this.config.set(buildConfigKeyMembers(group, pluginKey), new ArrayList<String>());
		return save();
	}
	
	@Override
	public boolean deleteGroup(final String group, final String pluginKey) {
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return false;
		}
		
		final List<String> uuids = this.config.getStringList(buildConfigKeyMembers(group, pluginKey));
		for(final String uuid: uuids) {
			final String configKeyPlayer = buildConfigKeyPlayerGroup(UUID.fromString(uuid), group, pluginKey);
			this.config.set(configKeyPlayer, null);
		}
		
		this.config.set(configKey, null);
		return save();
	}
	
	@Override
	public boolean isGroup(final String group, final String pluginKey) {
		return this.config.contains(buildConfigKeyGroup(group, pluginKey));
	}
	
	@Override
	public boolean addMember(final OfflinePlayer player, final String group, final String pluginKey) {
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return false;
		}
		
		final String configKeyMembers = buildConfigKeyMembers(group, pluginKey);
		final String uuid = player.getUniqueId().toString();
		final List<String> uuids = this.config.getStringList(configKeyMembers);
		if(uuids.contains(uuid)) {
			return false;
		}
		uuids.add(uuid);
		
		this.config.set(configKeyMembers, uuids);
		return save();
	}
	
	@Override
	public boolean removeMember(final OfflinePlayer player, final String group, final String pluginKey) {
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return false;
		}
		
		final String configKeyMembers = buildConfigKeyMembers(group, pluginKey);
		final String uuid = player.getUniqueId().toString();
		final List<String> uuids = this.config.getStringList(configKeyMembers);
		if(!uuids.contains(uuid)) {
			return false;
		}
		uuids.remove(uuid);
		
		this.config.set(configKeyMembers, uuids);
		return save();
	}
	
	@Override
	public boolean isMember(final OfflinePlayer player, final String group, final String pluginKey) {
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return false;
		}
		
		final String configKeyMembers = buildConfigKeyMembers(group, pluginKey);
		final String uuid = player.getUniqueId().toString();
		final List<String> uuids = this.config.getStringList(configKeyMembers);
		return uuids.contains(uuid);
	}
	
	@Override
	public Optional<List<OfflinePlayer>> getMembers(final String group, final String pluginKey) {
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		
		final String configKeyMembers = buildConfigKeyMembers(group, pluginKey);
		final List<OfflinePlayer> members = this.config.getStringList(configKeyMembers)
				.stream().map(uuid -> Bukkit.getOfflinePlayer(UUID.fromString(uuid))).collect(Collectors.toList());
		
		return Optional.of(members);
	}
	
	@Override
	public Optional<List<UUID>> getMemberIDs(final String group, final String pluginKey){
		final String configKey = buildConfigKeyGroup(group, pluginKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		
		final String configKeyMembers = buildConfigKeyMembers(group, pluginKey);
		final List<UUID> members = this.config.getStringList(configKeyMembers)
				.stream().map(uuid -> UUID.fromString(uuid)).collect(Collectors.toList());
		
		return Optional.of(members);
	}
	
	@Override
	public List<String> getGroups(final String pluginKey){
		final String configKey = buildConfigKeyGroups(pluginKey);
		final List<String> groups = new ArrayList<String>();
		if(!this.config.contains(configKey)) {
			return groups;
		}
		
		final ConfigurationSection section = this.config.getConfigurationSection(configKey);
		for(final String key: section.getKeys(false)) {
			groups.add(key);
		}
		
		return groups;
	}
	
	@Override
	public List<String> getGroups(final OfflinePlayer player, final String pluginKey){
		final List<String> groups = new ArrayList<String>();
		for(final String group: getGroups(pluginKey)) {
			if(isMember(player, group, pluginKey)) {
				groups.add(group);
			}
		}
		return groups;
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final String data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.STRING, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final int data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.INT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final long data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.LONG, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final float data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final double data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final boolean data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final List<String> data) {
		this.config.set(buildConfigKeyGroupData(group, ConfigDataType.LIST, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public Optional<String> getString(final String group, final String pluginKey, final String dataKey) {
		final String data = this.config.getString(buildConfigKeyGroupData(group, ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.INT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.LONG, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) this.config.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyGroupData(group, ConfigDataType.LIST, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getStringList(configKey));
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final String data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.STRING, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final int data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.INT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final long data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.LONG, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final float data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.FLOAT, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final double data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.DOUBLE, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final boolean data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.BOOLEAN, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final List<String> data) {
		this.config.set(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.LIST, pluginKey, dataKey), data);
		return save();
	}

	@Override
	public Optional<String> getString(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String data = this.config.getString(buildConfigKeyPlayerGroupData(player, group, ConfigDataType.STRING, pluginKey, dataKey));
		if(data == null) {
			return Optional.empty();
		}
		return Optional.of(data);
	}

	@Override
	public Optional<Integer> getInt(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.INT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getInt(configKey));
	}

	@Override
	public Optional<Long> getLong(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.LONG, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getLong(configKey));
	}

	@Override
	public Optional<Float> getFloat(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.FLOAT, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of((float) this.config.getDouble(configKey));
	}

	@Override
	public Optional<Double> getDouble(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.DOUBLE, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getDouble(configKey));
	}

	@Override
	public Optional<Boolean> getBoolean(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.BOOLEAN, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getBoolean(configKey));
	}

	@Override
	public Optional<List<String>> getList(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey) {
		final String configKey = buildConfigKeyPlayerGroupData(player, group, ConfigDataType.LIST, pluginKey, dataKey);
		if(!this.config.contains(configKey)) {
			return Optional.empty();
		}
		return Optional.of(this.config.getStringList(configKey));
	}
	
	private static String buildConfigKeyGlobalData(final ConfigDataType type, final String pluginKey, final String dataKey) {
		return SingleYamlDataSource.CONFIG_TYPE_GLOBAL + "." + pluginKey + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerData(final UUID uuid, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return SingleYamlDataSource.CONFIG_TYPE_PLAYERS + "." + uuid.toString() + "." + pluginKey + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerData(final OfflinePlayer player, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return buildConfigKeyPlayerData(player.getUniqueId(), type, pluginKey, dataKey);
	}
	
	private static String buildConfigKeyGroups(final String pluginKey) {
		return SingleYamlDataSource.CONFIG_TYPE_GROUPS + "." + pluginKey;
	}
	
	private static String buildConfigKeyGroup(final String group, final String pluginKey) {
		return buildConfigKeyGroups(pluginKey) + "." + group;
	}
	
	private static String buildConfigKeyMembers(final String group, final String pluginKey) {
		return buildConfigKeyGroup(group, pluginKey) + "." + SingleYamlDataSource.CONFIG_TYPE_MEMBERS;
	}
	
	private static String buildConfigKeyGroupData(final String group, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return buildConfigKeyGroup(group, pluginKey) + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerGroup(final UUID uuid, final String group, final String pluginKey) {
		return SingleYamlDataSource.CONFIG_TYPE_PLAYERS + "." + uuid.toString() + "." + pluginKey + SingleYamlDataSource.CONFIG_TYPE_GROUPS + "." + group;
	}
	
	private static String buildConfigKeyPlayerGroupData(final UUID uuid, final String group, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return buildConfigKeyPlayerGroup(uuid, group, pluginKey) + "." + type.getKey() + "." + dataKey;
	}
	
	private static String buildConfigKeyPlayerGroupData(final OfflinePlayer player, final String group, final ConfigDataType type, final String pluginKey, final String dataKey) {
		return buildConfigKeyPlayerGroupData(player.getUniqueId(), group, type, pluginKey, dataKey);
	}
	
	private boolean save() {
		try {
			this.config.save(this.configFile);
			return true;
		}catch(final IOException ex) {
			return false;
		}
	}

}
