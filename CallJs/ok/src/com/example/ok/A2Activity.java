package com.example.ok;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * 图表
 */
public class A2Activity extends Activity {
	
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
		wv.loadUrl("file:///android_asset/echart/echarts1.html");
	//	webView.getSettings().setJavaScriptEnabled(true);
		//webView.addJavascriptInterface(new AndroidBridge(), "android"); //将那个实例化的函数类设置为”android"的js接口。
		wv.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result) {

				//Log.d(TAG, "onJsAlert(" + view + "," + url + "," + message + "," + result + ")");
				Toast.makeText(A2Activity.this, message, Toast.LENGTH_LONG).show();
				result.confirm();
				return true;
			}

		});//设置可以被java截获的js事件。
	}
	public void backBtn(View view){
		this.finish();
	}

	public void updateBtn(View view){
		Toast.makeText(A2Activity.this,"22",Toast.LENGTH_LONG).show();
		String date4="[\"900\",\"300\",\"900\",\"1200\",\"100\",\"1\",\"8\",\"2\",\"1000\",\"5\",\"99\"]";
		String date6 = "[\"02:50\",\"02:54\",\"02:58\",\"03:02\",\"03:06\",\"03:10\",\"03:14\",\"03:18\",\"03:22\",\"03:26\",\"03:30\"]";

		//wv.loadUrl("javascript:setContentInfo('"+date4+"','"+date6+"')");

		wv.loadUrl("javascript:setContentInfo2('5000','9:00')");
	}

}
