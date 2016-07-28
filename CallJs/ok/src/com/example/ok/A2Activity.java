package com.example.ok;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

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
		
	}
	public void backBtn(View view){
		this.finish();
	}

	public void updateBtn(View view){
		String[] ss={"200","240","260","300","500","600","700","400","600","400","800"};
		String s="十三月";
		wv.loadUrl("javascript:setContentInfo('"+ss+"','"+s+"')");
	}

}
