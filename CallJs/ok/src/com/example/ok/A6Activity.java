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
public class A6Activity extends Activity {

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
		wv.loadUrl("file:///android_asset/flotr2/chart.html");
		//	webView.getSettings().setJavaScriptEnabled(true);
		//webView.addJavascriptInterface(new AndroidBridge(), "android"); //将那个实例化的函数类设置为”android"的js接口。
		wv.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result) {

				//Log.d(TAG, "onJsAlert(" + view + "," + url + "," + message + "," + result + ")");
				Toast.makeText(A6Activity.this, message, Toast.LENGTH_LONG).show();
				result.confirm();
				return true;
			}

		});//设置可以被java截获的js事件。
		wv.addJavascriptInterface(new Contact(), "contact");
	}

	public void backBtn(View view) {
		this.finish();
	}

	public void updateBtn(View view) {
		//Toast.makeText(A4Activity.this,"22",Toast.LENGTH_LONG).show();
		String date4 = "[\"1900\",\"1300\",\"2900\",\"1200\",\"1600\",\"1400\",\"1700\",\"1500\",\"1300\",\"1500\",\"1199\"]";
		String date6 = "[\"01:50\",\"01:54\",\"01:58\",\"01:02\",\"02:06\",\"02:10\",\"02:14\",\"02:18\",\"02:22\",\"02:26\",\"02:30\"]";
		String date7 = "{\"date\":\"2009-04-06T16:38:00.000Z\",\"value\":124,\"volume\":54440322}";
		wv.loadUrl("javascript:setContentInfo('" + date7 + "')");
	}



	private class Contact{
		String bj ="[[1, -9], [2, 1], [3, 12], [4, 20],[5, 26], [6, 30], [7, 32], [8, 29],[9, 22], [10, 12], [11, 0], [12, -6]]";// First data series
		String sz = "[[1, 15], [2, 16], [3, 19], [4, 22],[5, 26], [6, 27], [7, 28], [8, 28],[9, 27], [10, 25], [11, 20], [12, 16]]"; //Second data series

		@JavascriptInterface
		public void loch(){
			Toast.makeText(A6Activity.this,"11111111111",Toast.LENGTH_SHORT).show();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					wv.loadUrl("javascript:setContentInfo('"+bj+"','"+sz+"')");
				}
			});

		}
	}
}