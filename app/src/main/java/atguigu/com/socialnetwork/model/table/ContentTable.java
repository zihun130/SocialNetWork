package atguigu.com.socialnetwork.model.table;

/**
 * Created by sun on 2017/7/1.
 */

public class ContentTable {
    public static final String TABLE_NAME="userinfo";
    public static final String COL_USERNAME="username";
    public static final String COL_HXID="hxid";
    public static final String COL_PHOTO="photo";
    public static final String COL_NICK="nick";

    public static final String CREAT_TABLE="create table "+TABLE_NAME+"("
            +COL_HXID+" text primary key,"
            +COL_USERNAME+" text,"
            +COL_PHOTO+" text,"
            +COL_NICK+" text)";
}
