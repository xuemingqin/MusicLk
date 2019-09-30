package com.example.musiclk;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

    public class WebViewActivity extends AppCompatActivity {
        private WebView webView;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_webview );
            Intent intent=getIntent();
            String url= intent.getStringExtra( "url" );
            webView=findViewById( R.id.webview_only );
            webView.setWebViewClient( new WebViewClient( ){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,String url) {
                    view.loadUrl( url );
                    return true;
                }
            } );
            webView.loadUrl( url );
//            webView.getSettings( ).setJavaScriptEnabled( true );
        }
        @Override
        public void onBackPressed() {
            if (webView.canGoBack( )) {
                webView.goBack( );
            } else {
                finish();
            }
        }

    }


