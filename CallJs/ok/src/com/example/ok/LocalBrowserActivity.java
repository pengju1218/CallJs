package com.example.ok;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocalBrowserActivity extends Activity {

    private static final String TAG = "LocalBrowser";
    private final Handler handler = new Handler();
    private WebView webView;
    private TextView textView;
    private Button button;

    private class AndroidBridge //这个类中提供各种js可调用的方法。
    {

        @JavascriptInterface
        public void callAndroid(final String arg) {
            handler.post(new Runnable() {
                public void run() {
                    Log.d(TAG, "calAndroid(" + arg + ")");
                    textView.setText(arg);
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        webView = (WebView) findViewById(R.id.web_view);
        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.button);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AndroidBridge(), "android"); //将那个实例化的函数类设置为”android"的js接口。
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {

                Log.d(TAG, "onJsAlert(" + view + "," + url + "," + message + "," + result + ")");
                Toast.makeText(LocalBrowserActivity.this, message, Toast.LENGTH_LONG).show();
                result.confirm();
                return true;
            }

        });//设置可以被java截获的js事件。

        webView.loadUrl("file:///android_asset/alert.html");
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Log.d(TAG, "onClick(" + v + ")");
                String s="我们都是中国人";
                webView.loadUrl("javascript:callJS('"+s+"')");  //java调用js的函数
            }
        });
    }

}