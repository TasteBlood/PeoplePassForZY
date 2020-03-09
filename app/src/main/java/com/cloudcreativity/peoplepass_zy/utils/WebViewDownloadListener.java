package com.cloudcreativity.peoplepass_zy.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/**
 * All Rights Reserved By CloudCreativity Tech.
 *
 * @author : created by Xu Xiwu
 * date-time: 2019/10/24 16:34
 * e-mail: xxw0701@sina.com
 */
public class WebViewDownloadListener implements DownloadListener {

    private Context context;

    public WebViewDownloadListener(Context context) {
        this.context = context;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }
}
