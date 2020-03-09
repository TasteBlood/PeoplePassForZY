package com.cloudcreativity.peoplepass_zy.main;

import android.content.Intent;

import com.cloudcreativity.peoplepass_zy.base.BaseDialogImpl;
import com.cloudcreativity.peoplepass_zy.base.CommonWebActivity;
import com.cloudcreativity.peoplepass_zy.databinding.ActivityMainBinding;
import com.cloudcreativity.peoplepass_zy.entity.BannerEntity;
import com.cloudcreativity.peoplepass_zy.pass.PassIndexActivity;
import com.cloudcreativity.peoplepass_zy.utils.APIService;
import com.cloudcreativity.peoplepass_zy.utils.AppConfig;
import com.cloudcreativity.peoplepass_zy.utils.BannerImageLoader;
import com.cloudcreativity.peoplepass_zy.utils.DefaultObserver;
import com.cloudcreativity.peoplepass_zy.utils.HttpUtils;
import com.cloudcreativity.peoplepass_zy.utils.ToastUtils;
import com.cloudcreativity.peoplepass_zy.utils.UpdateManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel {
    private ActivityMainBinding binding;
    private MainActivity context;
    private BaseDialogImpl baseDialog;

    private List<String> bannerImages = new ArrayList<>();
    private List<String> bannerTitles = new ArrayList<>();

    MainModel(ActivityMainBinding binding, final MainActivity context) {
        this.binding = binding;
        this.context = context;
        this.baseDialog = context;

        binding.refreshMain.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                loadBanner();
                UpdateManager.checkVersion(context,context);
            }
        });
    }

    public void onLawClick(){
        CommonWebActivity.startActivity(context,"法律法规",APIService.HOST_APP+APIService.URL.URL_LAW+"?citLibId="+AppConfig.APP_AREA_CODE+"&type="+AppConfig.APP_TYPE);
    }

    public void onPeopleClick(){
        CommonWebActivity.startActivity(context,"民行工作",APIService.HOST_APP+APIService.URL.URL_CASE+"?citLibId="+AppConfig.APP_AREA_CODE+"&type="+AppConfig.APP_TYPE);
    }

    public void onPublicClick(){
        CommonWebActivity.startActivity(context,"公益诉讼",APIService.HOST_APP+APIService.URL.URL_PUBLIC+"?citLibId="+AppConfig.APP_AREA_CODE+"&type="+AppConfig.APP_TYPE);
    }

    public void onOrganizationClick(){
        CommonWebActivity.startActivity(context,"机构简介",APIService.HOST_APP+APIService.URL.URL_ORGANIZATION+"?citLibId="+AppConfig.APP_AREA_CODE+"&type="+AppConfig.APP_TYPE);

    }

    public void onClassicCaseClick(){
        CommonWebActivity.startActivity(context,"经典案例",APIService.HOST_APP+APIService.URL.URL_CLASSIC+"?citLibId="+AppConfig.APP_AREA_CODE+"&type="+AppConfig.APP_TYPE);
    }
    public void onPassClick(){
        context.startActivity(new Intent(context,PassIndexActivity.class));
    }

    private void loadBanner(){
        HttpUtils.getInstance().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<String>(baseDialog,false) {
                    @Override
                    public void onSuccess(String t) {
                        binding.refreshMain.finishRefreshing();
                        try{
                            Type type = new TypeToken<List<BannerEntity>>() {
                            }.getType();
                            bannerTitles.clear();
                            bannerImages.clear();
                            final List<BannerEntity> list = new Gson().fromJson(t, type);
                            for(BannerEntity entity:list){
                                bannerTitles.add(entity.getTitle());
                                bannerImages.add(entity.getPicUrl());
                            }
                            binding.bannerMain.setImageLoader(new BannerImageLoader())
                                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                                    .setBannerAnimation(Transformer.FlipHorizontal)
                                    .setImages(bannerImages)
                                    .setBannerTitles(bannerTitles)
                                    .setIndicatorGravity(BannerConfig.RIGHT)
                                    .start();

                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onFail(ExceptionReason msg) {
                        binding.refreshMain.finishRefreshing();
                    }
                });
    }
}
