package com.example.roman.dynamicbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class PageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        tv = (TextView) findViewById(R.id.full_tv_content);
        wv = (WebView) findViewById(R.id.url_content);
        backbt = (Button) findViewById(R.id.back);

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Page page = getIntent().getParcelableExtra("page");

        if(page.is_plain == 1)
        {
            tv.setText(page.plain_content);
        }
        else
        {
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl(page.url_content);
        }
    }

    Button backbt;
    TextView tv;
    WebView wv;
}
