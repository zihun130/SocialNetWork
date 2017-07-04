package atguigu.com.socialnetwork.model.dbdao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.socialnetwork.model.bean.InvitationInfo;
import atguigu.com.socialnetwork.model.bean.UserInfoBean;
import atguigu.com.socialnetwork.model.db.DBHelper;
import atguigu.com.socialnetwork.model.table.ContactTable;
import atguigu.com.socialnetwork.model.table.InvitationTable;

/**
 * Created by sun on 2017/7/3.
 */

public class InvitationDAO {

    private final DBHelper dbHelper;

    public InvitationDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //添加邀请
    public void addInvitation(InvitationInfo invitationInfo) {
        if (invitationInfo == null) {
            return;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InvitationTable.COL_HXID, invitationInfo.getUserInfoBean().getHxid());
        contentValues.put(InvitationTable.COL_REASON, invitationInfo.getReason());
        contentValues.put(InvitationTable.COL_USERNAME, invitationInfo.getUserInfoBean().getName());
        contentValues.put(InvitationTable.COL_STATE, invitationInfo.getStates().ordinal());

        database.replace(ContactTable.TABLE_NAME, null, contentValues);
    }


    //获取所有邀请信息
    public List<InvitationInfo> getInvitations() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select * from " + InvitationTable.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        ArrayList<InvitationInfo> infoList = new ArrayList<>();
        while (cursor.moveToNext()) {
            InvitationInfo info = new InvitationInfo();
            info.setReason(cursor.getString(cursor.getColumnIndex(InvitationTable.COL_REASON)));
            info.setStates(int2InviteStatus(cursor.getInt(cursor.getColumnIndex(InvitationTable.COL_STATE))));

            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(InvitationTable.COL_HXID)));
            userInfoBean.setName(cursor.getString(cursor.getColumnIndex(InvitationTable.TABLE_NAME)));
            info.setUserInfoBean(userInfoBean);

            infoList.add(info);
        }

        cursor.close();
        return infoList;
    }


    //删除邀请
    public void removeInvitation(String hxId) {
        if (TextUtils.isEmpty(hxId)) {
            return;
        }
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(InvitationTable.TABLE_NAME, InvitationTable.COL_HXID + "=?", new String[]{hxId});
    }

    //更新邀请状态
    public void updataInvitationStatus(InvitationInfo.InvitationStatus status, String hxId) {
        if (TextUtils.isEmpty(hxId)) {
            return;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InvitationTable.COL_STATE, status.ordinal());
        database.update(InvitationTable.TABLE_NAME, contentValues, InvitationTable.COL_HXID + "=?", new String[]{hxId});
    }


    // 将int类型状态转换为邀请的状态
    private InvitationInfo.InvitationStatus int2InviteStatus(int intStatus) {

        if (intStatus == InvitationInfo.InvitationStatus.NEW_INVITE.ordinal()) {
            return InvitationInfo.InvitationStatus.NEW_INVITE;
        }

        if (intStatus == InvitationInfo.InvitationStatus.INVITE_ACCEPT.ordinal()) {
            return InvitationInfo.InvitationStatus.INVITE_ACCEPT;
        }

        if (intStatus == InvitationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER.ordinal()) {
            return InvitationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER;
        }
        return null;
    }
}
