package com.example.hc.photo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.content.DialogInterface;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private WebView webView;
//    @SuppressLint("JavascriptInterface")

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.main_webview);

        webView.setVerticalScrollbarOverlay(true);
        //设置WebView支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受所有网站的证书
            }
        });

        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

//        webView.setBlockNetworkImage(false);

//        String url = "http://192.168.1.40/webViewAd/test.html";
//        String url = "http://192.168.1.40/webViewAd/small_video_detail_bottom_banner_new/?data=%5b%7b%22clickUrl%22%3a%22http%3a%2f%2f192.168.1.40%2fandroid0923%2fadDetail%2f1%22%2c%22images%22%3a%22http%3a%2f%2ffile.yrdsp.com%2fupload%2f20190912%2f97066a704bbf4e8c96c56d4d4dd5f1b4.jpg%22%7d%2c%7b%22clickUrl%22%3a%22http%3a%2f%2f192.168.1.40%2fandroid0923%2fadDetail%2f%22%2c%22images%22%3a%22https%3a%2f%2fqn12.tool.lu%2f201910%2f19%2f1120188LYcInWeA2kvG0ew_278x81.png%22%7d%5d";
        String url = "https://4g.cyzxs.cn/app.html?appId=105780&sid=1";
        webView.loadUrl(url);

        //在js中调用本地java方法
        webView.addJavascriptInterface(new JsInterface(this), "android");

        //添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());




    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void adClick(String name) {
            System.out.println(name);
//            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        }
//        public void getVersionName () {
//            String msg = "000";
//            return String;
//        }
    }


}
