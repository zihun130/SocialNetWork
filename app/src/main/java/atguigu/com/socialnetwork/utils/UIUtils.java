package atguigu.com.socialnetwork.utils;

import android.content.Context;
import android.widget.Toast;

import atguigu.com.socialnetwork.common.MyApplication;

/**
 * Created by sun on 2017/7/3.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }

    public static void UIThread(Runnable runnable){
        if(MyApplication.getPid()==android.os.Process.myTid()){
            runnable.run();
        }else {
            MyApplication.getHandler().post(runnable);
        }
    }

    public static void showToast(final String message){
        UIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
