package atguigu.com.socialnetwork.model;

import android.content.Context;

import atguigu.com.socialnetwork.model.db.DBHelper;
import atguigu.com.socialnetwork.model.dbdao.ContactDAO;
import atguigu.com.socialnetwork.model.dbdao.InvitationDAO;

/**
 * Created by sun on 2017/7/3.
 */

public class HelperManager {

    private final DBHelper dbHelper;
    private final ContactDAO contactDAO;
    private final InvitationDAO invitationDAO;

    public HelperManager(Context context, String name) {
        dbHelper = new DBHelper(context, name);
        contactDAO = new ContactDAO(dbHelper);
        invitationDAO = new InvitationDAO(dbHelper);
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public ContactDAO getContactDAO() {
        return contactDAO;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public InvitationDAO getInvitationDAO() {
        return invitationDAO;
    }

}
