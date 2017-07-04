package atguigu.com.socialnetwork.controller.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.hyphenate.easeui.ui.EaseContactListFragment;

import atguigu.com.socialnetwork.R;
import atguigu.com.socialnetwork.utils.UIUtils;

/**
 * Created by sun on 2017/7/3.
 */

public class ContactFragment extends EaseContactListFragment {

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        initHeadView();

        titleBar.setRightImageResource(R.drawable.em_add_public_group);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddContactActivity.class));
            }
        });
    }

    private void initHeadView() {
        View headview = View.inflate(getActivity(), R.layout.head_view, null);
        LinearLayout ll_new_friends = (LinearLayout) headview.findViewById(R.id.ll_new_friends);
        LinearLayout ll_groups = (LinearLayout) headview.findViewById(R.id.ll_groups);

        listView.addHeaderView(headview);

        ll_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("创建群聊");
            }
        });

        ll_new_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("邀请好友");
            }
        });
    }
}
