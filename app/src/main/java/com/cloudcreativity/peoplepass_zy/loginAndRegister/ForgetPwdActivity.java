package com.cloudcreativity.peoplepass_zy.loginAndRegister;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cloudcreativity.peoplepass_zy.R;
import com.cloudcreativity.peoplepass_zy.base.BaseActivity;
import com.cloudcreativity.peoplepass_zy.databinding.ActivityForgetPwdBinding;

/**
 * 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {
    private ForgetPwdModel model;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgetPwdBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_pwd);
        setSupportActionBar(binding.tlbForget);
        binding.tlbForget.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.setForgetModel(model = new ForgetPwdModel(this,this,binding));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.stopTimer();
    }
}
