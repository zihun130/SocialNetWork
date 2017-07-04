package atguigu.com.socialnetwork000.controller.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import atguigu.com.socialnetwork000.R;
import atguigu.com.socialnetwork000.base.BaseActivity;
import atguigu.com.socialnetwork000.model.Model;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @InjectView(R.id.login_et_username)
    EditText loginEtUsername;
    @InjectView(R.id.login_et_password)
    EditText loginEtPassword;
    @InjectView(R.id.login_btn_register)
    Button loginBtnRegister;
    @InjectView(R.id.login_btn_login)
    Button loginBtnLogin;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login_btn_register, R.id.login_btn_login})
    public void onViewClicked(View view) {
        final String name = loginEtUsername.getText().toString().trim();
        final String pwd = loginEtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            showToast("账户或密码不能为空");
            return;
        }


        switch (view.getId()) {
            case R.id.login_btn_register:
                Model.getInstance().getThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(name,pwd);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("注册成功");
                                }
                            });

                        } catch (final HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                   showToast(e.getMessage());
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.login_btn_login:
                Model.getInstance().getThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().login(name, pwd, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                String currentUser = EMClient.getInstance().getCurrentUser();

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("登录成功");
                                    }
                                });
                                finish();
                            }

                            @Override
                            public void onError(int i, final String s) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast(s);
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
                break;
        }
    }
}
