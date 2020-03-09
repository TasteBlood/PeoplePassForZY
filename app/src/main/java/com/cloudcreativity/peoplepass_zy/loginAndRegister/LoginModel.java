package com.cloudcreativity.peoplepass_zy.loginAndRegister;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.cloudcreativity.peoplepass_zy.base.BaseDialogImpl;
import com.cloudcreativity.peoplepass_zy.databinding.ActivityLoginBinding;
import com.cloudcreativity.peoplepass_zy.entity.UserEntity;
import com.cloudcreativity.peoplepass_zy.main.MainActivity;
import com.cloudcreativity.peoplepass_zy.utils.DefaultObserver;
import com.cloudcreativity.peoplepass_zy.utils.HttpUtils;
import com.cloudcreativity.peoplepass_zy.utils.SPUtils;
import com.cloudcreativity.peoplepass_zy.utils.StrUtils;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel {
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> pwd = new ObservableField<>();
    private ActivityLoginBinding binding;
    private LoginActivity context;
    private BaseDialogImpl baseDialog;

    LoginModel(ActivityLoginBinding binding, LoginActivity context) {
        this.binding = binding;
        this.context = context;
        this.baseDialog = context;
    }

    /**
     * 登录按钮点击
     */
    public void onLoginClick(){
        if(TextUtils.isEmpty(phone.get())||!StrUtils.isPhone(phone.get())){
            binding.tilPhone.setError("手机号格式不正确");
            return;
        }
        binding.tilPhone.setError(null);
        if(TextUtils.isEmpty(pwd.get())){
            binding.tilPwd.setError("密码不能为空");
            return;
        }
        binding.tilPwd.setError(null);

        HttpUtils.getInstance().login(phone.get(),pwd.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<String>(baseDialog,true) {
                    @Override
                    public void onSuccess(String t) {
                        //保存用户数据
                        UserEntity userEntity = new Gson().fromJson(t, UserEntity.class);
                        if(userEntity!=null){
                            SPUtils.get().putInt(SPUtils.Config.UID,userEntity.getId());
                            SPUtils.get().putString(SPUtils.Config.TOKEN,userEntity.getToken());
                            SPUtils.get().setUser(t);
                            SPUtils.get().putBoolean(SPUtils.Config.IS_LOGIN,true);
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            context.finish();
                        }
                    }

                    @Override
                    public void onFail(ExceptionReason msg) {

                    }
                });
    }

    /**
     * 注册按钮点击
     */
    public void onRegisterClick(){
        context.startActivity(new Intent(context,RegisterActivity.class));
    }

    /**
     * 忘记密码点击
     */
    public void onForgetClick(){
        context.startActivity(new Intent(context,ForgetPwdActivity.class));
    }
}
