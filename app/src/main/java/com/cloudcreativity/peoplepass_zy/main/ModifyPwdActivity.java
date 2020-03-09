package com.cloudcreativity.peoplepass_zy.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cloudcreativity.peoplepass_zy.R;
import com.cloudcreativity.peoplepass_zy.base.BaseActivity;
import com.cloudcreativity.peoplepass_zy.databinding.ActivityModifyPwdBinding;

/**
 * 修改密码
 */
public class ModifyPwdActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModifyPwdBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_pwd);

        binding.setModifyModel(new ModifyPwdModel(this,this,binding));
    }
}
