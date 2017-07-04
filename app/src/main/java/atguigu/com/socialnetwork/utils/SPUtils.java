package atguigu.com.socialnetwork.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sun on 2017/7/4.
 */

public class SPUtils {
    public static final String NEW_INVITATE = "newInvitate";
    private SharedPreferences sp;

    private SPUtils(){}
    private static SPUtils spUtils=new SPUtils();
    public static SPUtils getInstance(){
        return spUtils;
    }

    public void init(Context context,String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public void save(String key,Object values){
        SharedPreferences.Editor edit = sp.edit();
        if(values instanceof Boolean){
            edit.putBoolean(key, (Boolean) values).commit();
        }
        if(values instanceof String){
            edit.putString(key, (String) values).commit();
        }
    }

    public Boolean getValues(String key){
        return sp.getBoolean(key,false);
    }
}
