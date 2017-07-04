package atguigu.com.socialnetwork000.model;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sun on 2017/7/2.
 */

public class Model {
    private Context context;

    private Model(){}
    private static Model instance=new Model();
    public static Model getInstance(){
        return instance;
    }

    public void init(Context context){
        this.context= context;
    }

    private ExecutorService service= Executors.newCachedThreadPool();
    public ExecutorService getThread(){
        return service;
    }
}
