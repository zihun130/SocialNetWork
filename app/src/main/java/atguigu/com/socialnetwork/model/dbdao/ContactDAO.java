package atguigu.com.socialnetwork.model.dbdao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.socialnetwork.model.bean.UserInfoBean;
import atguigu.com.socialnetwork.model.db.DBHelper;
import atguigu.com.socialnetwork.model.table.ContactTable;

/**
 * Created by sun on 2017/7/3.
 */

public class ContactDAO {

    private final DBHelper dbHelper;

    public ContactDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //获取全部联系人
    public List<UserInfoBean> getContact() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String sql = "select * from " + ContactTable.TABLE_NAME + " where " + ContactTable.COL_IS_CONTACT + "=1";
        Cursor cursor = database.rawQuery(sql, null);
        List<UserInfoBean> userlist = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfoBean userInfo = new UserInfoBean();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USERNAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            userlist.add(userInfo);
        }
        cursor.close();
        return userlist;
    }

    //通过环信hxid获取单人信息
    public UserInfoBean getContactByHx(String hxId) {
        if (TextUtils.isEmpty(hxId)) {
            return null;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select * from " + ContactTable.TABLE_NAME + " where " + ContactTable.COL_HXID + "=?";
        Cursor cursor = database.rawQuery(sql, new String[]{hxId});
        UserInfoBean userInfo = new UserInfoBean();
        while (cursor.moveToNext()) {
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_USERNAME)));
        }
        cursor.close();
        return userInfo;
    }

    //通过环信id获取用户联系人信息
    public List<UserInfoBean> getContactsByHx(List<String> hxIds) {
        if (hxIds == null || hxIds.size() == 0) {
            return null;
        }

        ArrayList<UserInfoBean> uselist = new ArrayList<>();
        for (int i = 0; i < hxIds.size(); i++) {
            UserInfoBean contactByHx = getContactByHx(hxIds.get(i));
            if (contactByHx != null) {
                uselist.add(contactByHx);
            }
        }
        return uselist;
    }

    //保存单个联系人
    public void saveContact(UserInfoBean userInfoBean, boolean isMyContact) {
        if (userInfoBean != null) {
            return;
        }
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactTable.COL_HXID, userInfoBean.getHxid());
        contentValues.put(ContactTable.COL_NICK, userInfoBean.getNick());
        contentValues.put(ContactTable.COL_USERNAME, userInfoBean.getName());
        contentValues.put(ContactTable.COL_PHOTO, userInfoBean.getPhoto());
        contentValues.put(ContactTable.COL_IS_CONTACT, isMyContact ? 1 : 0);

        database.replace(ContactTable.TABLE_NAME, null, contentValues);
    }


    //保存联系人信息
    public void saveContacts(List<UserInfoBean> contacts, boolean isMyContact) {
        if (contacts == null || contacts.size() == 0) {
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            saveContact(contacts.get(i), isMyContact);
        }
    }

    //删除联系人
    public void deleteContactByHxId(String HxId) {
        if (TextUtils.isEmpty(HxId)) {
            return;
        }
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(ContactTable.TABLE_NAME, ContactTable.COL_HXID + "=?", new String[]{HxId});
    }


}
