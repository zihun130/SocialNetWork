package atguigu.com.socialnetwork.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by sun on 2017/7/1.
 */

public class MyApplication extends Application {



    private static Context context;
    private static int pid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        initHXSdk();
        Model.getInstance().init(this);
        context=this;
        pid=android.os.Process.myPid();
        handler=new Handler();
    }

    private void initHXSdk() {
        //配置文件
        EMOptions options = new EMOptions();
        //是否自动接受个人邀请
        options.setAcceptInvitationAlways(false);
        //自动接受群邀请
        options.setAutoAcceptGroupInvitation(false);
        //初始化SDK
        EaseUI.getInstance().init(this,options);
    }

    public static Context getContext() {
        return context;
    }
    public static int getPid() {
        return pid;
    }

    public static Handler getHandler() {
        return handler;
    }
}
