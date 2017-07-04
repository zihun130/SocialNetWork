package atguigu.com.socialnetwork.controller.avtivity;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import atguigu.com.socialnetwork.R;
import atguigu.com.socialnetwork.base.BaseActivity;
import atguigu.com.socialnetwork.controller.fragment.ContactFragment;
import atguigu.com.socialnetwork.controller.fragment.ConversationFragment;
import atguigu.com.socialnetwork.controller.fragment.SettingsFragment;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.rb_main_conversation)
    RadioButton rbMainConversation;
    @InjectView(R.id.rb_main_contact)
    RadioButton rbMainContact;
    @InjectView(R.id.rb_main_setting)
    RadioButton rbMainSetting;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    public void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });

    }

    private void switchFragment(int checkedId) {
        Fragment fragment=null;
        switch (checkedId) {
            case R.id.rb_main_conversation :
                fragment = new ConversationFragment();
                break;
            case R.id.rb_main_contact :
                fragment = new ContactFragment();
                break;
            case R.id.rb_main_setting :
                fragment = new SettingsFragment();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fl,fragment)
                .commit();
    }

    @Override
    public void initData() {
        switchFragment(R.id.rb_main_conversation);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }


}
