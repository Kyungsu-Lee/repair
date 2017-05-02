package com.example.lmasi.repair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    TextView tV1;
    LinearLayout MAIN_LL;

    private Handler mHandler;
    private Runnable mRunnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        int width = dm.widthPixels;
        int height = dm.heightPixels;



        mRunnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext()
                            , mainpage.class);
                startActivity(intent);

                finish();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);



    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }



}
