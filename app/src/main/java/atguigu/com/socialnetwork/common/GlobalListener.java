package atguigu.com.socialnetwork.common;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import atguigu.com.socialnetwork.model.Constant;
import atguigu.com.socialnetwork.model.bean.InvitationInfo;
import atguigu.com.socialnetwork.model.bean.UserInfoBean;
import atguigu.com.socialnetwork.utils.SPUtils;

/**
 * Created by sun on 2017/7/3.
 */

public class GlobalListener {


    private final LocalBroadcastManager instance;

    public GlobalListener(Context context) {
        EMClient.getInstance().contactManager().setContactListener(emContactListener);

        instance = LocalBroadcastManager.getInstance(context);
    }

    //设置全局监听
    EMContactListener emContactListener = new EMContactListener() {
        //增加联系人的时候回调  同意好友的邀请
        @Override
        public void onContactAdded(String usename) {
            Model.getInstance().getHelperManager().getContactDAO()
                    .saveContact(new UserInfoBean(usename,usename),true);

            instance.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));

        }

        //被删除的时候回调
        @Override
        public void onContactDeleted(String usename) {
            Model.getInstance().getHelperManager().getInvitationDAO().removeInvitation(usename);
            Model.getInstance().getHelperManager().getContactDAO().deleteContactByHxId(usename);
            instance.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));

        }

        //收到好友邀请  别人加你
        @Override
        public void onContactInvited(String usename, String reason) {
            InvitationInfo invitationInfo = new InvitationInfo();
            invitationInfo.setReason(reason);
            invitationInfo.setUserInfoBean(new UserInfoBean(usename,usename));
            Model.getInstance().getHelperManager().getInvitationDAO().addInvitation(invitationInfo);

            SPUtils.getInstance().save(SPUtils.NEW_INVITATE,true);

            instance.sendBroadcast(new Intent(Constant.NEW_INVITATION));
        }

        //好友的邀请被同意  你加别人,别人同意了
        @Override
        public void onContactAgreed(String usename) {
            InvitationInfo invitationInfo = new InvitationInfo();
            invitationInfo.setUserInfoBean(new UserInfoBean(usename,usename));
            invitationInfo.setReason("您的邀请已被同意");
            invitationInfo.setStates(InvitationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER);
            Model.getInstance().getHelperManager().getInvitationDAO().addInvitation(invitationInfo);

            SPUtils.getInstance().save(SPUtils.NEW_INVITATE,true);

            instance.sendBroadcast(new Intent(Constant.NEW_INVITATION));

        }

        //好友请求被拒绝  你加别人,别人拒绝了
        @Override
        public void onContactRefused(String usename) {

            SPUtils.getInstance().save(SPUtils.NEW_INVITATE,true);
            instance.sendBroadcast(new Intent(Constant.NEW_INVITATION));
        }
    };
}
