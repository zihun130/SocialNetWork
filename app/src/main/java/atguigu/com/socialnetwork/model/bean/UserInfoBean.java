package atguigu.com.socialnetwork.model.bean;

/**
 * Created by sun on 2017/7/1.
 */

public class UserInfoBean {
    private String name;
    private String nick;
    private String photo;
    private String hxid;

    public UserInfoBean() {
    }

    public UserInfoBean(String name, String nick, String photo, String hxid) {
        this.name = name;
        this.nick = nick;
        this.photo = photo;
        this.hxid = hxid;
    }

    public UserInfoBean(String hxid, String name) {
        this.hxid = hxid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
