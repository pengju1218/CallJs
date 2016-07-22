/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.example.ok;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import org.apache.cordova.*;

public class PhoneGapActivity extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
      //  super.setIntegerProperty("splashscreen", R.drawable.ic_launcher);
        super.init();
        appView.getSettings().setJavaScriptEnabled(true);

        appView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        appView.addJavascriptInterface(new PluginMethod(this, appView), "SM"); // 注意这里一句

        super.loadUrl("file:///android_asset/phone.html",4000);

    }

}
/**
 * Created with IntelliJ IDEA.
 * User: FakeMr
 * Date: 13-7-15
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
 class PluginMethod {
    private WebView webView;
    private DroidGap droidGap;

    public PluginMethod(DroidGap gap, WebView view) {
        webView = view;
        droidGap = gap;
    }

    /**
     * JS调用  用于更新App
     * @param path 更新门店的地址
     */
    @JavascriptInterface
    public void UpdateApp(final String path) {

        Log.e("---------------", path);  //注意这里日志输出
    }


    @JavascriptInterface
    public void push( String path) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:callJs()");
            }
        });
       //webView.loadUrl("javascript:");
        Log.e("---------------", path);  //注意这里日志输出
    }
}
