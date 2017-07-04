package atguigu.com.socialnetwork.controller.avtivity;

import android.content.Intent;
import android.os.CountDownTimer;

import com.hyphenate.chat.EMClient;

import atguigu.com.socialnetwork.R;
import atguigu.com.socialnetwork.base.BaseActivity;
import atguigu.com.socialnetwork.common.Model;

public class WelcomeActivity extends BaseActivity {

    private CountDownTimer countDownTimer;

    @Override
    public void initListener() {
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
              selectActivity();
            }
        }.start();
    }

    private void selectActivity() {
        Model.getInstance().getThread().execute(new Runnable() {
            @Override
            public void run() {
                boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
                if(loggedInBefore){
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }
}
