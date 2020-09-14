package com.skitskurr.datamanager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.skitskurr.datamanager.datasource.DataSource;

public class DataManager {
	
	public static class Global{

		public static boolean set(final String pluginKey, final String dataKey, final String data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
	
		public static boolean set(final String pluginKey, final String dataKey, final int data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
		
		public static boolean set(final String pluginKey, final String dataKey, final long data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
	
		public static boolean set(final String pluginKey, final String dataKey, final float data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
	
		public static boolean set(final String pluginKey, final String dataKey, final double data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
	
		public static boolean set(final String pluginKey, final String dataKey, final boolean data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}

		public static boolean set(final String pluginKey, final String dataKey, final List<String> data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
		
			return optionalSource.get().set(pluginKey, dataKey, data);
		}
	
		public static Optional<String> getString(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getString(pluginKey, dataKey);
		}
	
		public static String getString(final String pluginKey, final String dataKey, final String other) {
			return getString(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<Integer> getInt(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getInt(pluginKey, dataKey);
		}
	
		public static int getInt(final String pluginKey, final String dataKey, final int other) {
			return getInt(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<Long> getLong(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getLong(pluginKey, dataKey);
		}
	
		public static long getLong(final String pluginKey, final String dataKey, final long other) {
			return getLong(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<Float> getFloat(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getFloat(pluginKey, dataKey);
		}
	
		public static float getFloat(final String pluginKey, final String dataKey, final float other) {
			return getFloat(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<Double> getDouble(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getDouble(pluginKey, dataKey);
		}
	
		public static double getDouble(final String pluginKey, final String dataKey, final double other) {
			return getDouble(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<Boolean> getBoolean(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getBoolean(pluginKey, dataKey);
		}
	
		public static boolean getBoolean(final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(pluginKey, dataKey).orElse(other);
		}
	
		public static Optional<List<String>> getList(final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
		
			return optionalSource.get().getList(pluginKey, dataKey);
		}
	
		public static List<String> getList(final String pluginKey, final String dataKey, final List<String> other) {
			return getList(pluginKey, dataKey).orElse(other);
		}
	
	}
	
	public static class Players{

		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final String data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final String data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final int data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final int data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final long data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final long data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final float data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final float data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final double data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final double data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final boolean data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final boolean data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}

		public static boolean set(final UUID uuid, final String pluginKey, final String dataKey, final List<String> data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String pluginKey, final String dataKey, final List<String> data) {
			return set(player.getUniqueId(), pluginKey, dataKey, data);
		}
		
		public static Optional<String> getString(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getString(uuid, pluginKey, dataKey);
		}
		
		public static String getString(final UUID uuid, final String pluginKey, final String dataKey, final String other) {
			return getString(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<String> getString(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getString(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static String getString(final OfflinePlayer player, final String pluginKey, final String dataKey, final String other) {
			return getString(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<Integer> getInt(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getInt(uuid, pluginKey, dataKey);
		}
		
		public static int getInt(final UUID uuid, final String pluginKey, final String dataKey, final int other) {
			return getInt(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Integer> getInt(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getInt(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static int getInt(final OfflinePlayer player, final String pluginKey, final String dataKey, final int other) {
			return getInt(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<Long> getLong(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getLong(uuid, pluginKey, dataKey);
		}
		
		public static long getLong(final UUID uuid, final String pluginKey, final String dataKey, final long other) {
			return getLong(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Long> getLong(final Player player, final String pluginKey, final String dataKey){
			return getLong(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static long getLong(final OfflinePlayer player, final String pluginKey, final String dataKey, final long other) {
			return getLong(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<Float> getFloat(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getFloat(uuid, pluginKey, dataKey);
		}
		
		public static float getFloat(final UUID uuid, final String pluginKey, final String dataKey, final float other) {
			return getFloat(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Float> getFloat(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getFloat(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static float getFloat(final OfflinePlayer player, final String pluginKey, final String dataKey, final float other) {
			return getFloat(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<Double> getDouble(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getDouble(uuid, pluginKey, dataKey);
		}
		
		public static double getDouble(final UUID uuid, final String pluginKey, final String dataKey, final double other) {
			return getDouble(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Double> getDouble(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getDouble(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static double getDouble(final OfflinePlayer player, final String pluginKey, final String dataKey, final double other) {
			return getDouble(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<Boolean> getBoolean(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getBoolean(uuid, pluginKey, dataKey);
		}
		
		public static boolean getBoolean(final UUID uuid, final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Boolean> getBoolean(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getBoolean(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static boolean getBoolean(final OfflinePlayer player, final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
		public static Optional<List<String>> getList(final UUID uuid, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getList(uuid, pluginKey, dataKey);
		}
		
		public static List<String> getList(final UUID uuid, final String pluginKey, final String dataKey, final List<String> other) {
			return getList(uuid, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<List<String>> getList(final OfflinePlayer player, final String pluginKey, final String dataKey){
			return getList(player.getUniqueId(), pluginKey, dataKey);
		}
		
		public static List<String> getList(final OfflinePlayer player, final String pluginKey, final String dataKey, final List<String> other) {
			return getList(player.getUniqueId(), pluginKey, dataKey, other);
		}
		
	}
	
	public static class Groups{
		
		public static boolean addGroup(final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().addGroup(group, pluginKey);
		}
		
		public static boolean deleteGroup(final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().deleteGroup(group, pluginKey);
		}
		
		public static boolean isGroup(final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().isGroup(group, pluginKey);
		}
		
		public static boolean addMember(final UUID uuid, final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().addMember(uuid, group, pluginKey);
		}
		
		public static boolean addMember(final OfflinePlayer player, final String group, final String pluginKey) {
			return addMember(player.getUniqueId(), group, pluginKey);
		}
		
		public static boolean removeMember(final UUID uuid, final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().removeMember(uuid, group, pluginKey);
		}
		
		public static boolean removeMember(final OfflinePlayer player, final String group, final String pluginKey) {
			return removeMember(player.getUniqueId(), group, pluginKey);
		}
		
		public static boolean isMember(final UUID uuid, final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().isMember(uuid, group, pluginKey);
		}
		
		public static boolean isMember(final OfflinePlayer player, final String group, final String pluginKey) {
			return isMember(player.getUniqueId(), group, pluginKey);
		}
		
		public static Optional<List<OfflinePlayer>> getMembers(final String group, final String pluginKey) {
			final Optional<List<UUID>> memberIDs = getMemberIDs(group, pluginKey);
			// if we have no IDs we can't map them on the players
			if(memberIDs.isEmpty()) {
				return Optional.empty();
			}
			
			return Optional.of(memberIDs.get().stream().map(uuid -> Bukkit.getOfflinePlayer(uuid)).collect(Collectors.toList()));
		}
		
		public static Optional<List<UUID>> getMemberIDs(final String group, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getMemberIDs(group, pluginKey);
		}
		
		public static Optional<List<String>> getGroups(final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return Optional.of(optionalSource.get().getGroups(pluginKey));
		}
		
		public static Optional<List<String>> getGroups(final UUID uuid, final String pluginKey) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return Optional.of(optionalSource.get().getGroups(uuid, pluginKey));
		}
		
		public static Optional<List<String>> getGroups(final OfflinePlayer player, final String pluginKey) {
			return getGroups(player.getUniqueId(), pluginKey);
		}

		public static boolean set(final String group, final String pluginKey, final String dataKey, final String data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final String group, final String pluginKey, final String dataKey, final int data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final String group, final String pluginKey, final String dataKey, final long data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final String group, final String pluginKey, final String dataKey, final float data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final String group, final String pluginKey, final String dataKey, final double data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final String group, final String pluginKey, final String dataKey, final boolean data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}

		public static boolean set(final String group, final String pluginKey, final String dataKey, final List<String> data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(group, pluginKey, dataKey, data);
		}
		
		public static Optional<String> getString(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getString(group, pluginKey, dataKey);
		}
		
		public static String getString(final String group, final String pluginKey, final String dataKey, final String other) {
			return getString(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Integer> getInt(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getInt(group, pluginKey, dataKey);
		}
		
		public static int getInt(final String group, final String pluginKey, final String dataKey, final int other) {
			return getInt(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Long> getLong(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getLong(group, pluginKey, dataKey);
		}
		
		public static long getLong(final String group, final String pluginKey, final String dataKey, final long other) {
			return getLong(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Float> getFloat(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getFloat(group, pluginKey, dataKey);
		}
		
		public static float getFloat(final String group, final String pluginKey, final String dataKey, final float other) {
			return getFloat(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Double> getDouble(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getDouble(group, pluginKey, dataKey);
		}
		
		public static double getDouble(final String group, final String pluginKey, final String dataKey, final double other) {
			return getDouble(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Boolean> getBoolean(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getBoolean(group, pluginKey, dataKey);
		}
		
		public static boolean getBoolean(final String group, final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<List<String>> getList(final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getList(group, pluginKey, dataKey);
		}
		
		public static List<String> getList(final String group, final String pluginKey, final String dataKey, final List<String> other) {
			return getList(group, pluginKey, dataKey).orElse(other);
		}

		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final String data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final String data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final int data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final int data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final long data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final long data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final float data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final float data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final double data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final double data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final boolean data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final boolean data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}

		public static boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final List<String> data) {
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot write data
			if(!optionalSource.isPresent()) {
				return false;
			}
			
			return optionalSource.get().set(uuid, group, pluginKey, dataKey, data);
		}
		
		public static boolean set(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final List<String> data) {
			return set(player.getUniqueId(), group, pluginKey, dataKey, data);
		}
		
		public static Optional<String> getString(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getString(uuid, group, pluginKey, dataKey);
		}
		
		public static String getString(final UUID uuid, final String group, final String pluginKey, final String dataKey, final String other) {
			return getString(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<String> getString(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getString(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static String getString(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final String other) {
			return getString(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<Integer> getInt(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getInt(uuid, group, pluginKey, dataKey);
		}
		
		public static int getInt(final UUID uuid, final String group, final String pluginKey, final String dataKey, final int other) {
			return getInt(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Integer> getInt(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getInt(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static int getInt(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final int other) {
			return getInt(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<Long> getLong(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getLong(uuid, group, pluginKey, dataKey);
		}
		
		public static long getLong(final UUID uuid, final String group, final String pluginKey, final String dataKey, final long other) {
			return getLong(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Long> getLong(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getLong(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static long getLong(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final long other) {
			return getLong(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<Float> getFloat(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getFloat(uuid, group, pluginKey, dataKey);
		}
		
		public static float getFloat(final UUID uuid, final String group, final String pluginKey, final String dataKey, final float other) {
			return getFloat(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Float> getFloat(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getFloat(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static float getFloat(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final float other) {
			return getFloat(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<Double> getDouble(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getDouble(uuid, group, pluginKey, dataKey);
		}
		
		public static double getDouble(final UUID uuid, final String group, final String pluginKey, final String dataKey, final double other) {
			return getDouble(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Double> getDouble(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getDouble(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static double getDouble(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final double other) {
			return getDouble(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<Boolean> getBoolean(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getBoolean(uuid, group, pluginKey, dataKey);
		}
		
		public static boolean getBoolean(final UUID uuid, final String group, final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<Boolean> getBoolean(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getBoolean(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static boolean getBoolean(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final boolean other) {
			return getBoolean(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
		public static Optional<List<String>> getList(final UUID uuid, final String group, final String pluginKey, final String dataKey){
			final Optional<DataSource> optionalSource = getDataSource();
			// if no data source is present we cannot read data
			if(!optionalSource.isPresent()) {
				return Optional.empty();
			}
			
			return optionalSource.get().getList(uuid, group, pluginKey, dataKey);
		}
		
		public static List<String> getList(final UUID uuid, final String group, final String pluginKey, final String dataKey, final List<String> other) {
			return getList(uuid, group, pluginKey, dataKey).orElse(other);
		}
		
		public static Optional<List<String>> getList(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey){
			return getList(player.getUniqueId(), group, pluginKey, dataKey);
		}
		
		public static List<String> getList(final OfflinePlayer player, final String group, final String pluginKey, final String dataKey, final List<String> other) {
			return getList(player.getUniqueId(), group, pluginKey, dataKey, other);
		}
		
	}
	
	private static Optional<DataSource> getDataSource(){
		final Optional<Main> optionalPlugin = Main.getCurrent();
		// if no plugin instance is present we cannot access the data source
		if(!optionalPlugin.isPresent()) {
			return Optional.empty();
		}
		
		return optionalPlugin.get().getDataSource();
		
	}
	
}
