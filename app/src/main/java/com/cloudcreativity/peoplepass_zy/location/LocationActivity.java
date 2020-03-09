package com.cloudcreativity.peoplepass_zy.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.cloudcreativity.peoplepass_zy.R;
import com.cloudcreativity.peoplepass_zy.base.BaseActivity;
import com.cloudcreativity.peoplepass_zy.base.BaseApp;
import com.cloudcreativity.peoplepass_zy.databinding.ActivityLocationBinding;
import com.cloudcreativity.peoplepass_zy.utils.ToastUtils;

/**
 * 这是地址选择
 */
public class LocationActivity extends BaseActivity {

    private ActivityLocationBinding binding;
    private LocationModel locationModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)&&
                (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)){
            binding = DataBindingUtil.setContentView(this, R.layout.activity_location);
            locationModel = new LocationModel(this, binding);
            binding.setLocationModel(locationModel);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},100);
            }else{
                ToastUtils.showShortToast(BaseApp.app,"请在手机设置中打开定位权限");
                onBackPressed();
            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationModel.destroy();
        binding.mvLocation.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((Manifest.permission.ACCESS_COARSE_LOCATION.equals(permissions[0])&&grantResults[0]==PackageManager.PERMISSION_GRANTED)&&
                Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[1])&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            //显示
            binding = DataBindingUtil.setContentView(this, R.layout.activity_location);
            locationModel = new LocationModel(this, binding);
            binding.setLocationModel(locationModel);
        }else{
            ToastUtils.showShortToast(BaseApp.app,"请在手机设置中打开定位权限");
            onBackPressed();
        }
    }
}
