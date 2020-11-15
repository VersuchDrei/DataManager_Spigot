package com.versuchdrei.datamanager.datasource.database;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.versuchdrei.datamanager.datasource.DataSource;
import com.versuchdrei.datamanager.utils.StringUtils;

/**
 * the superclass for all backends using databases
 * @author VersuchDrei
 * @version 1.0
 */
public abstract class DBDataSource implements DataSource{
	

	private static final String TABLE_STRINGS = "Skitskurr_DataManager_Strings";
	private static final String TABLE_INTS = "Skitskurr_DataManager_Ints";
	private static final String TABLE_LONGS = "Skitskurr_DataManager_Longs";
	private static final String TABLE_FLOATS = "Skitskurr_DataManager_Floats";
	private static final String TABLE_DOUBLES = "Skitskurr_DataManager_Doubles";
	private static final String TABLE_BOOLEANS = "Skitskurr_DataManager_Names";
	private static final String TABLE_LISTS = "Skitskurr_DataManager_Lists";

	private static final String TABLE_PLAYER_STRINGS = "Skitskurr_DataManager_PlayerStrings";
	private static final String TABLE_PLAYER_INTS = "Skitskurr_DataManager_PlayerInts";
	private static final String TABLE_PLAYER_LONGS = "Skitskurr_DataManager_PlayerLongs";
	private static final String TABLE_PLAYER_FLOATS = "Skitskurr_DataManager_PlayerFloats";
	private static final String TABLE_PLAYER_DOUBLES = "Skitskurr_DataManager_PlayerDoubles";
	private static final String TABLE_PLAYER_BOOLEANS = "Skitskurr_DataManager_PlayerNames";
	private static final String TABLE_PLAYER_LISTS = "Skitskurr_DataManager_PlayerLists";

	private static final String TABLE_GROUPS = "Skitskurr_DataManager_Groups";
	private static final String TABLE_GROUP_MEMBERS = "Skitskurr_DataManager_GroupMembers";
	
	private static final String TABLE_GROUP_STRINGS = "Skitskurr_DataManager_GroupStrings";
	private static final String TABLE_GROUP_INTS = "Skitskurr_DataManager_GroupInts";
	private static final String TABLE_GROUP_LONGS = "Skitskurr_DataManager_GroupLongs";
	private static final String TABLE_GROUP_FLOATS = "Skitskurr_DataManager_GroupFloats";
	private static final String TABLE_GROUP_DOUBLES = "Skitskurr_DataManager_GroupDoubles";
	private static final String TABLE_GROUP_BOOLEANS = "Skitskurr_DataManager_GroupNames";
	private static final String TABLE_GROUP_LISTS = "Skitskurr_DataManager_GroupLists";
	
	private static final String TABLE_GROUP_MEMBER_STRINGS = "Skitskurr_DataManager_GroupMemberStrings";
	private static final String TABLE_GROUP_MEMBER_INTS = "Skitskurr_DataManager_GroupMemberInts";
	private static final String TABLE_GROUP_MEMBER_LONGS = "Skitskurr_DataManager_GroupMemberLongs";
	private static final String TABLE_GROUP_MEMBER_FLOATS = "Skitskurr_DataManager_GroupMemberFloats";
	private static final String TABLE_GROUP_MEMBER_DOUBLES = "Skitskurr_DataManager_GroupMemberDoubles";
	private static final String TABLE_GROUP_MEMBER_BOOLEANS = "Skitskurr_DataManager_GroupMemberNames";
	private static final String TABLE_GROUP_MEMBER_LISTS = "Skitskurr_DataManager_GroupMemberLists";
	
	private static final String COLUMN_PLAYER = "Player";
	private static final String COLUMN_GROUP = "Group";
	private static final String COLUMN_PLUGIN_KEY = "PluginKey";
	private static final String COLUMN_DATA_KEY = "DataKey";
	private static final String COLUMN_DATA = "Data"; // value would be a more straightforward name here, but is a keyword in most database languages
	
	/**
	 * Creates a new table with the given columns, if it does not already exist. 
	 * An existence check for the table is required in implementations of this method.
	 * @param name the name of the table to create
	 * @param columns the columns of the table to create
	 */
	protected abstract void createTable(String name, List<Column> columns);
	
	private void createTable(final String name, final Column... columns) {
		createTable(name, Arrays.asList(columns));
	}
	
	/**
	 * inserts the given values into the table or updates it, if the unique key already exists
	 * @param table the name of the table to update the values in
	 * @param columnEntries the columns and their respective values
	 * @return true on success, otherwise false
	 */
	protected abstract boolean updateValue(String table, List<UpdateColumnEntry> columnEntries);
	
	private boolean updateValue(final String table, final UpdateColumnEntry... columnEntries) {
		return updateValue(table, Arrays.asList(columnEntries));
	}
	
	/**
	 * deletes all rows from the given table that match the given keys
	 * @param table the name of the table to delete from
	 * @param keys a list of keys to identify the rows
	 * @return true on success, otherwise false
	 */
	protected abstract boolean deleteValue(final String table, final List<ColumnEntry> keys);
	
	private boolean deleteValue(final String table, final ColumnEntry... keys) {
		return deleteValue(table, Arrays.asList(keys));
	}
	
	/**
	 * gets the given column entry of the row identified by the given keys in the given table
	 * @param table the name of the table to get the result from
	 * @param column the column to get
	 * @param keys a list of keys to identify the row
	 * @return a result containing the entry, if it exists
	 */
	protected abstract Result getResult(String table, String column, List<ColumnEntry> keys);
	
	private Result getResult(final String table, final String column, final ColumnEntry... keys) {
		return getResult(table, column, Arrays.asList(keys));
	}
	
	/**
	 * checks if an entry with the given keys exists in the given table
	 * @param table the name of the table to check for the entry
	 * @param keys the keys to identify the entry
	 * @return true if an entry exists, otherwise false
	 */
	protected abstract boolean exists(String table, List<ColumnEntry> keys);
	
	private boolean exists(final String table, final ColumnEntry... keys) {
		return exists(table, Arrays.asList(keys));
	}
	
	private boolean set(final String table, final String pluginKey, final String dataKey, final ColumnType type, final String data) {
		return updateValue(table,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA, type, data, false));
	}
	
	private boolean set(final String table, final String pluginKey, final UUID uuid, final String dataKey, final ColumnType type, final String data) {
		return updateValue(table,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString(), true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA, type, data, false));
	}
	
	private boolean set(final String table, final String pluginKey, final String group, final String dataKey, final ColumnType type, final String data) {
		return updateValue(table,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA, type, data, false));
	}
	
	private boolean set(final String table, final String pluginKey, final String group, final UUID uuid, final String dataKey, final ColumnType type, final String data) {
		return updateValue(table,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString(), true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_DATA, type, data, false));
	}
	
	private Result get(final String table, final String pluginKey, final String dataKey) {
		return getResult(table, DBDataSource.COLUMN_DATA, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey));
	}
	
	private Result get(final String table, final String pluginKey, final UUID uuid, final String dataKey) {
		return getResult(table, DBDataSource.COLUMN_DATA, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString()),
				new ColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey));
	}
	
	private Result get(final String table, final String pluginKey, final String group, final String dataKey) {
		return getResult(table, DBDataSource.COLUMN_DATA, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group),
				new ColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey));
	}
	
	private Result get(final String table, final String pluginKey, final String group, final UUID uuid, final String dataKey) {
		return getResult(table, DBDataSource.COLUMN_DATA, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group),
				new ColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString()),
				new ColumnEntry(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, dataKey));
	}
	
	@Override
	public void setup() {
		createTable(DBDataSource.TABLE_STRINGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		createTable(DBDataSource.TABLE_INTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.INT));
		createTable(DBDataSource.TABLE_LONGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.LONG));
		createTable(DBDataSource.TABLE_FLOATS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.FLOAT));
		createTable(DBDataSource.TABLE_DOUBLES,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.DOUBLE));
		createTable(DBDataSource.TABLE_BOOLEANS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.BOOLEAN));
		// this table does not have a unique key because multiple entries with the same key represent multiple entries of the list
		createTable(DBDataSource.TABLE_LISTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		
		createTable(DBDataSource.TABLE_PLAYER_STRINGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		createTable(DBDataSource.TABLE_PLAYER_INTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.INT));
		createTable(DBDataSource.TABLE_PLAYER_LONGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.LONG));
		createTable(DBDataSource.TABLE_PLAYER_FLOATS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.FLOAT));
		createTable(DBDataSource.TABLE_PLAYER_DOUBLES,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.DOUBLE));
		createTable(DBDataSource.TABLE_PLAYER_BOOLEANS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.BOOLEAN));
		// this table does not have a unique key because multiple entries with the same key represent multiple entries of the list
		createTable(DBDataSource.TABLE_PLAYER_LISTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		
		createTable(DBDataSource.TABLE_GROUPS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true));
		createTable(DBDataSource.TABLE_GROUP_MEMBERS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true));

		createTable(DBDataSource.TABLE_GROUP_STRINGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		createTable(DBDataSource.TABLE_GROUP_INTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.INT));
		createTable(DBDataSource.TABLE_GROUP_LONGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.LONG));
		createTable(DBDataSource.TABLE_GROUP_FLOATS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.FLOAT));
		createTable(DBDataSource.TABLE_GROUP_DOUBLES,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.DOUBLE));
		createTable(DBDataSource.TABLE_GROUP_BOOLEANS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.BOOLEAN));
		// this table does not have a unique key because multiple entries with the same key represent multiple entries of the list
		createTable(DBDataSource.TABLE_GROUP_LISTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, false, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));

		createTable(DBDataSource.TABLE_GROUP_MEMBER_STRINGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
		createTable(DBDataSource.TABLE_GROUP_MEMBER_INTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.INT));
		createTable(DBDataSource.TABLE_GROUP_MEMBER_LONGS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.LONG));
		createTable(DBDataSource.TABLE_GROUP_MEMBER_FLOATS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.FLOAT));
		createTable(DBDataSource.TABLE_GROUP_MEMBER_DOUBLES,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.DOUBLE));
		createTable(DBDataSource.TABLE_GROUP_MEMBER_BOOLEANS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, true, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY, true),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.BOOLEAN));
		// this table does not have a unique key because multiple entries with the same key represent multiple entries of the list
		createTable(DBDataSource.TABLE_GROUP_MEMBER_LISTS,
				new Column(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, false, new ForeignKey(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP)),
				new Column(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA_KEY, ColumnType.STRING_KEY),
				new Column(DBDataSource.COLUMN_DATA, ColumnType.STRING_VALUE));
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final String data) {
		return set(DBDataSource.TABLE_STRINGS, pluginKey, dataKey, ColumnType.STRING_VALUE, data);
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final int data) {
		return set(DBDataSource.TABLE_INTS, pluginKey, dataKey, ColumnType.INT, "" + data);
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final long data) {
		return set(DBDataSource.TABLE_LONGS, pluginKey, dataKey, ColumnType.LONG, "" + data);
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final float data) {
		return set(DBDataSource.TABLE_FLOATS, pluginKey, dataKey, ColumnType.FLOAT, "" + data);
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final double data) {
		return set(DBDataSource.TABLE_DOUBLES, pluginKey, dataKey, ColumnType.BOOLEAN, "" + data);
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final boolean data) {
		return set(DBDataSource.TABLE_BOOLEANS, pluginKey, dataKey, ColumnType.BOOLEAN, data ? "1" : "0");
	}

	@Override
	public boolean set(final String pluginKey, final String dataKey, final List<String> data) {
		return set(DBDataSource.TABLE_LISTS, pluginKey, dataKey, ColumnType.STRING_LIST, StringUtils.listToString(data));
	}

	@Override
	public Optional<String> getString(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_STRINGS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getString());
	}

	@Override
	public Optional<Integer> getInt(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_INTS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getInt());
	}

	@Override
	public Optional<Long> getLong(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_LONGS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getLong());
	}

	@Override
	public Optional<Float> getFloat(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_FLOATS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getFloat());
	}

	@Override
	public Optional<Double> getDouble(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_DOUBLES, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getDouble());
	}

	@Override
	public Optional<Boolean> getBoolean(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_BOOLEANS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getBoolean());
	}

	@Override
	public Optional<List<String>> getList(final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_LISTS, pluginKey, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(StringUtils.stringToList(result.getString()));
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final String data) {
		return set(DBDataSource.TABLE_PLAYER_STRINGS, pluginKey, uuid, dataKey, ColumnType.STRING_VALUE, data);
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final int data) {
		return set(DBDataSource.TABLE_PLAYER_INTS, pluginKey, uuid, dataKey, ColumnType.INT, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final long data) {
		return set(DBDataSource.TABLE_PLAYER_LONGS, pluginKey, uuid, dataKey, ColumnType.LONG, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final float data) {
		return set(DBDataSource.TABLE_PLAYER_FLOATS, pluginKey, uuid, dataKey, ColumnType.FLOAT, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final double data) {
		return set(DBDataSource.TABLE_PLAYER_DOUBLES, pluginKey, uuid, dataKey, ColumnType.DOUBLE, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final boolean data) {
		return set(DBDataSource.TABLE_PLAYER_BOOLEANS, pluginKey, uuid, dataKey, ColumnType.BOOLEAN, data ? "1" : "0");
	}

	@Override
	public boolean set(final UUID uuid, final String pluginKey, final String dataKey, final List<String> data) {
		return set(DBDataSource.TABLE_PLAYER_LISTS, pluginKey, uuid, dataKey, ColumnType.STRING_LIST, StringUtils.listToString(data));
	}

	@Override
	public Optional<String> getString(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_STRINGS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getString());
	}

	@Override
	public Optional<Integer> getInt(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_INTS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getInt());
	}

	@Override
	public Optional<Long> getLong(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_LONGS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getLong());
	}

	@Override
	public Optional<Float> getFloat(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_FLOATS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getFloat());
	}

	@Override
	public Optional<Double> getDouble(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_DOUBLES, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getDouble());
	}

	@Override
	public Optional<Boolean> getBoolean(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_BOOLEANS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getBoolean());
	}

	@Override
	public Optional<List<String>> getList(final UUID uuid, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_PLAYER_LISTS, pluginKey, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(StringUtils.stringToList(result.getString()));
	}

	@Override
	public boolean addGroup(final String group, final String pluginKey) {
		return updateValue(DBDataSource.TABLE_GROUPS,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true));
	}

	@Override
	public boolean deleteGroup(final String group, final String pluginKey) {
		return deleteValue(DBDataSource.TABLE_GROUPS,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true));
	}

	@Override
	public boolean isGroup(final String group, final String pluginKey) {
		return exists(DBDataSource.TABLE_GROUPS,
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group));
	}

	@Override
	public boolean addMember(final UUID uuid, final String group, final String pluginKey) {
		return updateValue(DBDataSource.TABLE_GROUP_MEMBERS,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString(), true));
	}

	@Override
	public boolean removeMember(final UUID uuid, final String group, final String pluginKey) {
		return deleteValue(DBDataSource.TABLE_GROUP_MEMBERS,
				new UpdateColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group, true),
				new UpdateColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString(), true));
	}

	@Override
	public boolean isMember(final UUID uuid, final String group, final String pluginKey) {
		return exists(DBDataSource.TABLE_GROUP_MEMBERS,
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group),
				new ColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString()));
	}

	@Override
	public Optional<List<UUID>> getMemberIDs(final String group, final String pluginKey) {
		final Result result = getResult(DBDataSource.TABLE_GROUP_MEMBERS, DBDataSource.COLUMN_PLAYER, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_GROUP, ColumnType.STRING_KEY, group));
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getList().stream().map(uuid -> UUID.fromString(uuid)).collect(Collectors.toList()));
	}

	@Override
	public List<String> getGroups(final String pluginKey) {
		final Result result = getResult(DBDataSource.TABLE_GROUPS, DBDataSource.COLUMN_GROUP, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey));
		
		return result.getList();
	}

	@Override
	public List<String> getGroups(final UUID uuid, final String pluginKey) {
		final Result result = getResult(DBDataSource.TABLE_GROUP_MEMBERS, DBDataSource.COLUMN_GROUP, 
				new ColumnEntry(DBDataSource.COLUMN_PLUGIN_KEY, ColumnType.STRING_KEY, pluginKey),
				new ColumnEntry(DBDataSource.COLUMN_PLAYER, ColumnType.STRING_KEY, uuid.toString()));
		
		return result.getList();
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final String data) {
		return set(DBDataSource.TABLE_GROUP_STRINGS, pluginKey, group, dataKey, ColumnType.STRING_VALUE, data);
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final int data) {
		return set(DBDataSource.TABLE_GROUP_INTS, pluginKey, group, dataKey, ColumnType.INT, "" + data);
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final long data) {
		return set(DBDataSource.TABLE_GROUP_LONGS, pluginKey, group, dataKey, ColumnType.LONG, "" + data);
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final float data) {
		return set(DBDataSource.TABLE_GROUP_FLOATS, pluginKey, group, dataKey, ColumnType.FLOAT, "" + data);
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final double data) {
		return set(DBDataSource.TABLE_GROUP_DOUBLES, pluginKey, group, dataKey, ColumnType.DOUBLE, "" + data);
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final boolean data) {
		return set(DBDataSource.TABLE_GROUP_BOOLEANS, pluginKey, group, dataKey, ColumnType.BOOLEAN, data ? "1" : "0");
	}

	@Override
	public boolean set(final String group, final String pluginKey, final String dataKey, final List<String> data) {
		return set(DBDataSource.TABLE_GROUP_LISTS, pluginKey, group, dataKey, ColumnType.STRING_VALUE, StringUtils.listToString(data));
	}

	@Override
	public Optional<String> getString(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_STRINGS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getString());
	}

	@Override
	public Optional<Integer> getInt(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_INTS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getInt());
	}

	@Override
	public Optional<Long> getLong(final String group, final String pluginKey, final String dataKey) {
final Result result = get(DBDataSource.TABLE_GROUP_LONGS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getLong());
	}

	@Override
	public Optional<Float> getFloat(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_FLOATS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getFloat());
	}

	@Override
	public Optional<Double> getDouble(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_DOUBLES, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getDouble());
	}

	@Override
	public Optional<Boolean> getBoolean(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_BOOLEANS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getBoolean());
	}

	@Override
	public Optional<List<String>> getList(final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_LISTS, pluginKey, group, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(StringUtils.stringToList(result.getString()));
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final String data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_STRINGS, pluginKey, group, uuid, dataKey, ColumnType.STRING_VALUE, data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final int data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_INTS, pluginKey, group, uuid, dataKey, ColumnType.INT, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final long data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_LONGS, pluginKey, group, uuid, dataKey, ColumnType.LONG, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final float data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_FLOATS, pluginKey, group, uuid, dataKey, ColumnType.FLOAT, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final double data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_DOUBLES, pluginKey, group, uuid, dataKey, ColumnType.DOUBLE, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final boolean data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_BOOLEANS, pluginKey, group, uuid, dataKey, ColumnType.BOOLEAN, "" + data);
	}

	@Override
	public boolean set(final UUID uuid, final String group, final String pluginKey, final String dataKey, final List<String> data) {
		return set(DBDataSource.TABLE_GROUP_MEMBER_LISTS, pluginKey, group, uuid, dataKey, ColumnType.STRING_VALUE, StringUtils.listToString(data));
	}

	@Override
	public Optional<String> getString(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_STRINGS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getString());
	}

	@Override
	public Optional<Integer> getInt(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_INTS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getInt());
	}

	@Override
	public Optional<Long> getLong(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_LONGS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getLong());
	}

	@Override
	public Optional<Float> getFloat(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_FLOATS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getFloat());
	}

	@Override
	public Optional<Double> getDouble(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_DOUBLES, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getDouble());
	}

	@Override
	public Optional<Boolean> getBoolean(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_BOOLEANS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.getBoolean());
	}

	@Override
	public Optional<List<String>> getList(final UUID uuid, final String group, final String pluginKey, final String dataKey) {
		final Result result = get(DBDataSource.TABLE_GROUP_MEMBER_LISTS, pluginKey, group, uuid, dataKey);
		
		if(result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(StringUtils.stringToList(result.getString()));
	}

}
