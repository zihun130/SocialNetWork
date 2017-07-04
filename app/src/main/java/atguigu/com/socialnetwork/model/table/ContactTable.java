package atguigu.com.socialnetwork.model.table;

/**
 * Created by sun on 2017/7/3.
 */

public class ContactTable {
    public static final String TABLE_NAME="contactinfo";
    public static final String COL_USERNAME="username";
    public static final String COL_HXID="userhxid";
    public static final String COL_PHOTO="userphoto";
    public static final String COL_NICK="usernick";
    public static final String COL_IS_CONTACT="contact";

    public static final String CREAT_TABLE="create table "+TABLE_NAME+"("
            +COL_HXID+" text primary key,"
            +COL_USERNAME+" text,"
            +COL_PHOTO+" text,"
            +COL_NICK+" text,"
            +COL_IS_CONTACT+" integer)";

}
