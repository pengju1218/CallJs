package com.example.ok;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;
    public Handler handler = new Handler();

    @JavascriptInterface
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //webview
        webView = (WebView) findViewById(R.id.webview);
        //允许JavaScript执行
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        //找到Html文件，也可以用网络上的文件
        webView.loadUrl("file:///android_asset/index.html");
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        webView.addJavascriptInterface(new Contact(), "contact");

    }

    public void fun(View view) {
        Toast.makeText(MainActivity.this, "222222222", Toast.LENGTH_LONG).show();
        String call = "javascript:sumToJava(1,2)";
        webView.loadUrl(call);

    }

    private final class Contact {
        //JavaScript调用此方法拨打电话
        @JavascriptInterface
        public void call(String phone) {
            //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
        }

        //Html调用此方法传递数据
        @JavascriptInterface
        public void showcontacts(int i) {

            //Toast.makeText(MainActivity.this,"222222222",Toast.LENGTH_LONG).show();

            final String json = "[{\"name\":\"zxx\", \"amount\":\"9999999\", \"phone\":\"18600012345\"}]";

            switch (i) {
                case 0:
                    //方法一
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            webView.loadUrl("javascript:show('" + json + "')");//鎵цhtml甯冨眬鏂囦欢涓殑javascript鍑芥暟浠ｇ爜--

                        }
                    });
                    break;

                case 1:
                    //方法一
                    webView.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            webView.loadUrl("javascript:show('" + json + "')");//鎵цhtml甯冨眬鏂囦欢涓殑javascript鍑芥暟浠ｇ爜--

                        }
                    });
                    break;

                case 2:
                    //方法三
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadUrl("javascript:show('" + json + "')");
                        }
                    });
                    break;
            }
        }
    }
}