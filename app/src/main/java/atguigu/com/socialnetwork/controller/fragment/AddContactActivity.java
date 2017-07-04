package atguigu.com.socialnetwork.controller.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import atguigu.com.socialnetwork.R;
import atguigu.com.socialnetwork.base.BaseActivity;
import atguigu.com.socialnetwork.common.Model;
import atguigu.com.socialnetwork.utils.UIUtils;
import butterknife.InjectView;

public class AddContactActivity extends BaseActivity {

    @InjectView(R.id.invite_btn_search)
    Button inviteBtnSearch;
    @InjectView(R.id.invite_et_search)
    EditText inviteEtSearch;
    @InjectView(R.id.invite_tv_username)
    TextView inviteTvUsername;
    @InjectView(R.id.invite_btn_add)
    Button inviteBtnAdd;
    @InjectView(R.id.invite_ll_item)
    LinearLayout inviteLlItem;
    @InjectView(R.id.activity_add_contact)
    LinearLayout activityAddContact;
    private String usename;

    @Override
    public void initListener() {
        inviteBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usename = inviteEtSearch.getText().toString().trim();
                if (TextUtils.isEmpty(usename)) {
                    UIUtils.showToast("用户名不能为空");
                }

                Model.getInstance().getThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        //去本地服务进行查询联系人

                        if (getUser()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    inviteLlItem.setVisibility(View.VISIBLE);
                                    inviteTvUsername.setText(usename);
                                }
                            });
                        } else {
                            UIUtils.showToast("未能找到联系人,请确定输入!!!");
                        }
                    }

                });
            }
        });

        inviteBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EMClient.getInstance().contactManager().addContact(usename, "求添加");
                    UIUtils.showToast("添加联系人成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    UIUtils.showToast(e.getMessage());
                }
            }
        });

    }

    private boolean getUser() {
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_contact;
    }

}
