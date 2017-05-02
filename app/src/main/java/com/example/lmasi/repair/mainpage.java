

package com.example.lmasi.repair;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by lmasi on 2016-05-24.
*/
public class mainpage extends Activity{

    RelativeLayout main_R;
    Drawable[] drawable;
    ImageView[] imgV;
    RelativeLayout.LayoutParams[] params;

    int draw_num ;      //initialize drawable numbers
    int imgV_num ;
    int param_num ;

    DrawingView DV;
    DrawingView DV2;


    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

         draw_num = 14;      //initialize drawable numbers
         imgV_num = 14;
         param_num = 14;

        drawable = new Drawable[draw_num];
        imgV = new ImageView[imgV_num];

        params = new RelativeLayout.LayoutParams[param_num];


        main_R = (RelativeLayout)findViewById(R.id.RR);

        //make_page(0,8);

       // make_form(8,12);


        //상단바
        imgV[0] = new ImageView(this);
        imgV[0].setBackgroundColor(Color.rgb(129,160,205));
        params[0] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 222);
        params[0].setMargins(0,0,0,0);
        imgV[0].setLayoutParams(params[0]);

        //막수리
        imgV[1] = new ImageView(this);
        drawable[1] = getResources().getDrawable(R.drawable.mac_head);
        imgV[1].setBackground(drawable[1]);
        params[1] = new RelativeLayout.LayoutParams(128, 136);
        params[1].setMargins(656,43,0,0);
        imgV[1].setLayoutParams(params[1]);

        //종
        imgV[2] = new ImageView(this);
        drawable[2] = getResources().getDrawable(R.drawable.ring);
        imgV[2].setBackground(drawable[2]);
        params[2] = new RelativeLayout.LayoutParams(109, 118);
        params[2].setMargins(1258,50,0,0);
        imgV[2].setLayoutParams(params[2]);

        //하단바
        imgV[3] = new ImageView(this);
        imgV[3].setBackgroundColor(Color.rgb(129,160,205));
        params[3] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 270);
        params[3].setMargins(0,2290,0,0);
        imgV[3].setLayoutParams(params[3]);

        // 홈
        imgV[4] = new ImageView(this);
        drawable[4] = getResources().getDrawable(R.drawable.ico_home);
        imgV[4].setBackground(drawable[4]);
        params[4] = new RelativeLayout.LayoutParams(132, 130);
        params[4].setMargins(103,2330,0,0);
        imgV[4].setLayoutParams(params[4]);

        //내 아이템
        imgV[5] = new ImageView(this);
        drawable[5] = getResources().getDrawable(R.drawable.my_item);
        imgV[5].setBackground(drawable[5]);
        params[5] = new RelativeLayout.LayoutParams(161, 153);
        params[5].setMargins(449,2330,0,0);
        imgV[5].setLayoutParams(params[5]);

        //즐겨찾기
        imgV[6] = new ImageView(this);
        drawable[6] = getResources().getDrawable(R.drawable.ico_star);
        imgV[6].setBackground(drawable[6]);
        params[6] = new RelativeLayout.LayoutParams(140, 133);
        params[6].setMargins(825,2330,0,0);
        imgV[6].setLayoutParams(params[6]);

        //settings
        imgV[7] = new ImageView(this);
        drawable[7] = getResources().getDrawable(R.drawable.ico_setting);
        imgV[7].setBackground(drawable[7]);
        params[7] = new RelativeLayout.LayoutParams(151, 156);
        params[7].setMargins(1180,2330,0,0);
        imgV[7].setLayoutParams(params[7]);




        imgV[8] = new ImageView(this);
        drawable[8] = getResources().getDrawable(R.drawable.main_bg2);
        imgV[8].setBackground(drawable[8]);
        params[8] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 979);
        params[8].setMargins(0,250,0,0);
        imgV[8].setLayoutParams(params[8]);


        imgV[9] = new ImageView(this);
        drawable[9] = getResources().getDrawable(R.drawable.main_bg1);
        imgV[9].setBackground(drawable[9]);
        params[9] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 979);
        params[9].setMargins(-20,1270,0,0);
        imgV[9].setLayoutParams(params[9]);

        imgV[10] = new ImageView(this);
        imgV[10].setBackgroundColor(Color.argb(170,158,158,159));
        params[10] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 979);
        params[10].setMargins(0,250,0,0);
        imgV[10].setLayoutParams(params[10]);

        imgV[11] = new ImageView(this);
        imgV[11].setBackgroundColor(Color.argb(135,238,200,200));
        params[11] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 979);
        params[11].setMargins(-20,1270,0,0);
        imgV[11].setLayoutParams(params[11]);





        for(int i=0; i < 12; i++)
        {
            main_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(mtouch);
        }


        DV = new DrawingView(this);
        DV.setColorformARGB(170,158,158,159);
        DV.setPosition(720, 773, 360);
        main_R.addView(DV);
       // DV.setOnTouchListener(mtouch);


        DV2 = new DrawingView(this);
        DV2.setColorformARGB(221,238,200,200);
        DV2.setPosition(720, 1772, 360);
        main_R.addView(DV2);
       // DV2.setOnTouchListener(mtouch);

        imgV[12] = new ImageView(this);
        drawable[12] = getResources().getDrawable(R.drawable.make_form);
        imgV[12].setBackground(drawable[12]);
        params[12] = new RelativeLayout.LayoutParams(464, 255);
        params[12].setMargins(480,620,0,0);
        imgV[12].setLayoutParams(params[12]);
        imgV[12].setOnTouchListener(mtouch);
        main_R.addView(imgV[12]);

        imgV[13] = new ImageView(this);
        drawable[13] = getResources().getDrawable(R.drawable.check_form);
        imgV[13].setBackground(drawable[13]);
        params[13] = new RelativeLayout.LayoutParams(464, 255);
        params[13].setMargins(480,1650,0,0);
        imgV[13].setLayoutParams(params[13]);
        imgV[13].setOnTouchListener(mtouch);
        main_R.addView(imgV[13]);

    }

    public class DrawingView extends View{

        public int X;
        public int Y;
        public int R;

        public Paint paint;


        public DrawingView(Context context) {
            super(context);
        }

        public void setPosition(int X, int Y, int R)
        {
            this.X = X;
            this.Y = Y;
            this.R = R;
        }

        public void setColorformARGB(int alpha, int R, int G, int B)
        {
            paint = new Paint();
            paint.setColor(Color.argb(alpha,R,G,B));
            paint.setAntiAlias(true);
        }

        public DrawingView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        public DrawingView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //원
            canvas.drawCircle(X, Y, R, paint);
        }


    }

    private View.OnTouchListener mtouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if ((ImageView) v == imgV[4]) {
                    Intent intent = new Intent(getApplicationContext()
                            , mainpage.class);
                    startActivity(intent);

                    finish();
                }

                else if((ImageView)v == imgV[5])
                {
                        finish();
                }

                else if ((ImageView)v == imgV[6])
                {
                    Intent intent = new Intent(getApplicationContext()
                            , MainActivity.class);
                    startActivity(intent);

                    finish();
                }

                else if ((ImageView)v == imgV[7])
                {try{
                    String out_srt = "";

                    File file = getFileStreamPath("text.txt");
                    FileOutputStream fos = new FileOutputStream(file);
                    Writer out = new OutputStreamWriter(fos, "UTF-8");
                    out.write(out_srt);
                    out.close();
                }catch(IOException e)
                {
                    throw new RuntimeException(e);
                }}

                //make form
                else if ((ImageView)v == imgV[12])
                {
                    Intent intent = new Intent(getApplicationContext()
                            , makeform.class);
                    startActivity(intent);

                    finish();

                }


                else if ((ImageView)v == imgV[13])
                {
                    Intent intent = new Intent(getApplicationContext() , check_page.class);
                    startActivity(intent);

                    finish();
                }



            }

            return true;
        }
    };
}


