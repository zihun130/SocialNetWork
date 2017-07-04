package atguigu.com.socialnetwork.model.bean;

/**
 * Created by sun on 2017/7/3.
 */

public class InvitationInfo {
    private UserInfoBean userInfoBean;
    private String reason;
    private InvitationStatus states;

    public InvitationInfo() {
    }

    public InvitationInfo(UserInfoBean userInfoBean, InvitationStatus states, String reason) {
        this.userInfoBean = userInfoBean;
        this.states = states;
        this.reason = reason;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public InvitationStatus getStates() {
        return states;
    }

    public void setStates(InvitationStatus states) {
        this.states = states;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public enum InvitationStatus {
        // contact invite status
        NEW_INVITE,// 新邀请
        INVITE_ACCEPT,//接受邀请
        INVITE_ACCEPT_BY_PEER,// 邀请被接受
    }
}
