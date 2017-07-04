package atguigu.com.socialnetwork.model.table;

/**
 * Created by sun on 2017/7/3.
 */

public class InvitationTable {
    public static final String TABLE_NAME="invitationinfo";
    public static final String COL_USERNAME="invitationname";
    public static final String COL_HXID="invitationhxid";
    public static final String COL_REASON="invitationreason";
    public static final String COL_STATE="invitationstate";

    public static final String CREAT_TABLE="create table "+TABLE_NAME+"("
            +COL_HXID+" text primary key,"
            +COL_USERNAME+" text,"
            +COL_REASON+" text,"
            +COL_STATE+" text)";
}
