package atguigu.com.socialnetwork.model.dbdao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import atguigu.com.socialnetwork.model.bean.UserInfoBean;
import atguigu.com.socialnetwork.model.db.ContentDB;
import atguigu.com.socialnetwork.model.table.ContentTable;

import static atguigu.com.socialnetwork.model.table.ContentTable.TABLE_NAME;

/**
 * Created by sun on 2017/7/1.
 */

public class ContentDAO {
    private final ContentDB contentDB;

    public ContentDAO(Context context) {
        contentDB = new ContentDB(context);
    }

    public void addData(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            throw new NullPointerException("数据不能为空");
        }

        SQLiteDatabase database = contentDB.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentTable.COL_HXID, userInfoBean.getHxid());
        contentValues.put(ContentTable.COL_NICK, userInfoBean.getNick());
        contentValues.put(ContentTable.COL_PHOTO, userInfoBean.getPhoto());
        contentValues.put(ContentTable.COL_USERNAME, userInfoBean.getName());
        database.replace(TABLE_NAME, null, contentValues);
    }

    public UserInfoBean getData(String hxid) {
        if (TextUtils.isEmpty(hxid)) {
            return null;
        }

        SQLiteDatabase database = contentDB.getWritableDatabase();
        String sql = "select * from " + ContentTable.TABLE_NAME
                + " where " + ContentTable.COL_HXID + "=?";
        Cursor cursor = database.rawQuery(sql, new String[]{hxid});
        UserInfoBean userInfoBean = new UserInfoBean();
        if (cursor.moveToNext()) {
            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(ContentTable.COL_HXID)));
            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(ContentTable.COL_HXID)));
            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(ContentTable.COL_HXID)));
            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(ContentTable.COL_HXID)));
        }
        cursor.close();
        return userInfoBean;
    }
}
