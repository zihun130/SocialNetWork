package atguigu.com.socialnetwork.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import atguigu.com.socialnetwork.R;
import atguigu.com.socialnetwork.controller.avtivity.LoginActivity;
import atguigu.com.socialnetwork.utils.UIUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/3.
 */

public class SettingsFragment extends Fragment {
    @InjectView(R.id.setting_btn_exit)
    Button settingBtnExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String currentUser = EMClient.getInstance().getCurrentUser();
        settingBtnExit.setText("退出登录(" + currentUser + ")");

        settingBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {

                        UIUtils.showToast("退出成功");
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        UIUtils.showToast(s);

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
