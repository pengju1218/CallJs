package com.example.ok;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * 图表
 */
public class A7Activity extends Activity {

	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 
		setContentView(R.layout.a1);
		wv = (WebView) findViewById(R.id.wv);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setUseWideViewPort(true);
		wv.getSettings().setSupportZoom(false);
		// 设置是否可缩放
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.requestFocus();
		wv.loadUrl("file:///android_asset/two/index.html");
		//	webView.getSettings().setJavaScriptEnabled(true);
		//webView.addJavascriptInterface(new AndroidBridge(), "android"); //将那个实例化的函数类设置为”android"的js接口。
		wv.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result) {

				//Log.d(TAG, "onJsAlert(" + view + "," + url + "," + message + "," + result + ")");
				Toast.makeText(A7Activity.this, message, Toast.LENGTH_LONG).show();
				result.confirm();
				return true;
			}

		});//设置可以被java截获的js事件。
		wv.addJavascriptInterface(new JsToJava(), "contact");
	}

	public void backBtn(View view) {
		this.finish();
	}
	private class JsToJava
	{

		@JavascriptInterface
		public void jsMethod(String  id,String paramFromJS)
		{
			//Log.i("CDH", paramFromJS);
			Toast.makeText(A7Activity.this,"js的id是" +id+",  js返回结果"+paramFromJS,Toast.LENGTH_SHORT).show();
			System.out.println("js返回结果" + paramFromJS);//处理返回的结果
		}
	}
	public void updateBtn(View view) {
			wv.loadUrl("javascript:getValueById('aa')");
	}



}