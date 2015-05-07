package jp.itnav.derushio.sqldatabaselib;

/**
 * Created by derushio on 15/01/13.
 */

public class DatabaseItem {
	public static final String TYPE_INTEGER_PRIMARY_KEY_AUTOINCREMENT = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String TYPE_NULL = "NULL";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_REAL = "REAL";
	public static final String TYPE_TEXT = "TEXT";
	public static final String TYPE_BLOB = "BLOB";

	public String name;
	public String type;
	public int index;

	public DatabaseItem(String name, String type) {
		this.name = name;
		this.type = type;
	}
}
