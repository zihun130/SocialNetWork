package atguigu.com.socialnetwork.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import atguigu.com.socialnetwork.model.table.ContactTable;
import atguigu.com.socialnetwork.model.table.InvitationTable;

/**
 * Created by sun on 2017/7/3.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactTable.CREAT_TABLE);
        db.execSQL(InvitationTable.CREAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
