package atguigu.com.socialnetwork000.model;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by sun on 2017/7/2.
 */

public class MyApplication extends Application {


    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initHxid();
        context=this;
    }

    private void initHxid() {
        EMOptions options=new EMOptions();
        //是否接受个人邀请
        options.setAcceptInvitationAlways(false);
        //是否接受群邀请
        options.setAutoAcceptGroupInvitation(false);

        EaseUI.getInstance().init(this,options);
    }

    public Context getContext() {
        return context;
    }
}
