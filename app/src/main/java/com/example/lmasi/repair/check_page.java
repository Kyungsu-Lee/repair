package com.example.lmasi.repair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by lmasi on 2016-05-28.
 */
public class check_page extends Activity{

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
        setContentView(R.layout.check_page);

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


        ScrollView SV = new ScrollView(this);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1950);
        param.setMargins(0,230,0,0);
        SV.setLayoutParams(param);
        main_R.addView(SV);

        for (int i = 0; i < 8; i++) {
            main_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(mtouch);
        }

        RelativeLayout sub_R = new RelativeLayout(this);
        sub_R.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        SV.addView(sub_R);

        Drawable phone = getResources().getDrawable(R.drawable.phone_ico);

        String str = "";
        int num = 0;

        try {
            File file = getFileStreamPath("text.txt");
            FileInputStream fis = new FileInputStream(file);
            Reader in = new InputStreamReader(fis);
            int size = fis.available();
            char[] buffer = new char[size];


            in.read(buffer);
            in.close();
            str = new String(buffer);

            for(int i=0; i<size; i++)
                if(str.charAt(i) == '$')
                    num++;


        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }




        for(int i=8; i<8+num; i++) {

            imgV[i] = new ImageView(this);
            drawable[i] = getResources().getDrawable(R.drawable.check_box);
            imgV[i].setBackground(drawable[i]);
            params[i] = new RelativeLayout.LayoutParams(1193, 335);
            params[i].setMargins(123, 150 * (num_box + 1) + 335 * num_box, 0, 0);
            imgV[i].setLayoutParams(params[i]);
            sub_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(mtouch);

            ICO[i] = new ImageView(this);
            ICO[i].setBackground(phone);
            I_param[i] = new RelativeLayout.LayoutParams(138, 258);
            I_param[i].setMargins(228, 150 * (num_box + 1) + 335 * num_box + 40, 0, 0);
            ICO[i].setLayoutParams(I_param[i]);
            sub_R.addView(ICO[i]);


            int index = str.indexOf("$");

            tV[i] = new TextView(this);
            tv_param[i] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv_param[i].setMargins(560, 150 * (num_box + 1) + 335 * num_box + 30, 0, 0);
            tV[i].setText(str.substring(0, index));
            tV[i].setLayoutParams(tv_param[i]);
            sub_R.addView(tV[i]);

            str = str.substring(index+1);


            num_box++;

            SV.invalidate();
            SV.requestLayout();
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

                else if (v == (View) imgV[8]) {
                    Intent intent = new Intent(getApplicationContext()
                            , phone_check.class);
                    startActivity(intent);
                }
                else if (v == (View) imgV[9]) {
                    Intent intent = new Intent(getApplicationContext()
                            , phone_check.class);
                    startActivity(intent);
                }
                else if (v == (View) imgV[10]) {
                    Intent intent = new Intent(getApplicationContext()
                            , phone_check.class);
                    startActivity(intent);
                }
                else if (v == (View) imgV[11]) {
                    Intent intent = new Intent(getApplicationContext()
                            , phone_check.class);
                    startActivity(intent);
                }

                else if (v == (View) imgV[12]) {
                    Intent intent = new Intent(getApplicationContext()
                            , phone_check.class);
                    startActivity(intent);
                }




            }

            return true;
        }
    };
}
