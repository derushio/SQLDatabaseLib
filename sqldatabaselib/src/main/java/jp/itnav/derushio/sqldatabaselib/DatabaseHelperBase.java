package jp.itnav.derushio.sqldatabaselib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derushio on 15/05/02.
 * ご利用方法
 * 登録したいアイテムをDataBaseItemのpublicで宣言して
 * mDarabaseItemsに突っ込んでもうだけです。
 */
public class DatabaseHelperBase extends SQLiteOpenHelper {
	private Context mContext;
	private String mTableName;
	private SQLiteDatabase.CursorFactory mFactory;
	private int mVersion;

	public DatabaseItem itemId = new DatabaseItem("id", DatabaseItem.TYPE_INTEGER_PRIMARY_KEY_AUTOINCREMENT);

	protected List<DatabaseItem> mDatabaseItems;

	public DatabaseHelperBase(Context context, String tableName, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, tableName, factory, version);
		mContext = context;
		mTableName = tableName;
		mFactory = factory;
		mVersion = version;

		mDatabaseItems = new ArrayList<>();
		mDatabaseItems.add(itemId);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(getCreateTableStatement());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);

		Cursor cursor = db.query(mTableName, null, null, null, null, null, null);
		cursor.moveToFirst();

		for (int i = 0; i < mDatabaseItems.size(); i++) {
			mDatabaseItems.get(i).index = cursor.getColumnIndex(mDatabaseItems.get(i).name);
		}
	}

	private String getCreateTableStatement() {
		String createTableStatement = "CREATE TABLE " + mTableName + "(";
		for (int i = 0; i < mDatabaseItems.size(); i++) {
			if (i != 0) {
				createTableStatement += ",";
			}
			createTableStatement += mDatabaseItems.get(i).name + " " + mDatabaseItems.get(i).type;
		}
		createTableStatement += ");";

		return createTableStatement;
	}
}
