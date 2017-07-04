package atguigu.com.socialnetwork.common;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import atguigu.com.socialnetwork.model.HelperManager;
import atguigu.com.socialnetwork.model.bean.UserInfoBean;
import atguigu.com.socialnetwork.model.dbdao.ContentDAO;
import atguigu.com.socialnetwork.utils.SPUtils;

/**
 * Created by sun on 2017/7/1.
 */

public class Model {

    private Context context;

    private ContentDAO contentDAO;
    private GlobalListener globalListener;
    private HelperManager manager;


    private Model() {
    }

    private static Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        contentDAO = new ContentDAO(context);

        globalListener = new GlobalListener(context);

    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getThread() {
        return service;
    }


    public void loginSuccess(UserInfoBean userInfoBean) {
        contentDAO.addData(userInfoBean);

        if (manager != null) {
            manager.close();
        }

        manager = new HelperManager(context, userInfoBean.getName() + ".db");
        SPUtils.getInstance().init(context,userInfoBean.getName());
    }

    public ContentDAO getContentDAO() {
        return contentDAO;
    }

    public HelperManager getHelperManager() {
        return manager;
    }
}
