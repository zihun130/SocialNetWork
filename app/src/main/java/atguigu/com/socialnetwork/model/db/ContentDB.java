package atguigu.com.socialnetwork.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import atguigu.com.socialnetwork.model.table.ContentTable;

/**
 * Created by sun on 2017/7/1.
 */

public class ContentDB extends SQLiteOpenHelper {
    public ContentDB(Context context) {
        super(context, "content.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContentTable.CREAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
