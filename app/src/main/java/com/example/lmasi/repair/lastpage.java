package com.example.lmasi.repair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lmasi on 2016-05-28.
 */
public class lastpage extends Activity {

    RelativeLayout main_R;
    Drawable[] drawable;
    ImageView[] imgV;
    RelativeLayout.LayoutParams[] params;
    ImageView[] ICO;
    Drawable[] I_drawable;
    RelativeLayout.LayoutParams[] I_param;
    RelativeLayout.LayoutParams[] tv_param;
    TextView[] tV;

    int draw_num ;      //initialize drawable numbers
    int imgV_num ;
    int param_num ;

    int num_box;


    private Handler mHandler;

    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_page);

        draw_num = 20;      //initialize drawable numbers
        imgV_num = 20;
        param_num = 20;

        num_box = 0;

        drawable = new Drawable[draw_num];
        imgV = new ImageView[imgV_num];
        ICO = new ImageView[imgV_num];

        params = new RelativeLayout.LayoutParams[param_num];
        I_param = new RelativeLayout.LayoutParams[param_num];
        tv_param = new RelativeLayout.LayoutParams[param_num];
        tV = new TextView[draw_num];


        main_R = (RelativeLayout) findViewById(R.id.RR);

        //make_page(0,8);

        // make_form(8,12);


        //상단바
        imgV[0] = new ImageView(this);
        imgV[0].setBackgroundColor(Color.rgb(129, 160, 205));
        params[0] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 222);
        params[0].setMargins(0, 0, 0, 0);
        imgV[0].setLayoutParams(params[0]);

        //막수리
        imgV[1] = new ImageView(this);
        drawable[1] = getResources().getDrawable(R.drawable.mac_head);
        imgV[1].setBackground(drawable[1]);
        params[1] = new RelativeLayout.LayoutParams(128, 136);
        params[1].setMargins(656, 43, 0, 0);
        imgV[1].setLayoutParams(params[1]);

        //종
        imgV[2] = new ImageView(this);
        drawable[2] = getResources().getDrawable(R.drawable.ring);
        imgV[2].setBackground(drawable[2]);
        params[2] = new RelativeLayout.LayoutParams(109, 118);
        params[2].setMargins(1258, 50, 0, 0);
        imgV[2].setLayoutParams(params[2]);

        //하단바
        imgV[3] = new ImageView(this);
        imgV[3].setBackgroundColor(Color.rgb(129, 160, 205));
        params[3] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 270);
        params[3].setMargins(0, 2290, 0, 0);
        imgV[3].setLayoutParams(params[3]);

        // 홈
        imgV[4] = new ImageView(this);
        drawable[4] = getResources().getDrawable(R.drawable.ico_home);
        imgV[4].setBackground(drawable[4]);
        params[4] = new RelativeLayout.LayoutParams(132, 130);
        params[4].setMargins(103, 2330, 0, 0);
        imgV[4].setLayoutParams(params[4]);

        //내 아이템
        imgV[5] = new ImageView(this);
        drawable[5] = getResources().getDrawable(R.drawable.my_item);
        imgV[5].setBackground(drawable[5]);
        params[5] = new RelativeLayout.LayoutParams(161, 153);
        params[5].setMargins(449, 2330, 0, 0);
        imgV[5].setLayoutParams(params[5]);

        //즐겨찾기
        imgV[6] = new ImageView(this);
        drawable[6] = getResources().getDrawable(R.drawable.ico_star);
        imgV[6].setBackground(drawable[6]);
        params[6] = new RelativeLayout.LayoutParams(140, 133);
        params[6].setMargins(825, 2330, 0, 0);
        imgV[6].setLayoutParams(params[6]);

        //settings
        imgV[7] = new ImageView(this);
        drawable[7] = getResources().getDrawable(R.drawable.ico_setting);
        imgV[7].setBackground(drawable[7]);
        params[7] = new RelativeLayout.LayoutParams(151, 156);
        params[7].setMargins(1180, 2330, 0, 0);
        imgV[7].setLayoutParams(params[7]);


        imgV[8] = new ImageView(this);
        drawable[8] = getResources().getDrawable(R.drawable.last);
        imgV[8].setBackground(drawable[8]);
        params[8] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1918);
        params[8].setMargins(50, 312, 0, 0);
        imgV[8].setLayoutParams(params[8]);

        for (int i = 0; i < 9; i++) {
            main_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(mtouch);
        }
    }

    private View.OnTouchListener mtouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (v == (View) imgV[4]) {
                    Intent intent = new Intent(getApplicationContext()
                            , mainpage.class);
                    startActivity(intent);

                    finish();
                } else if (v == (View) imgV[5]) {
                    finish();
                } else if (v == (View) imgV[6]) {
                    Intent intent = new Intent(getApplicationContext()
                            , MainActivity.class);
                    startActivity(intent);
                }




            }

            return true;
        }
    };
}
