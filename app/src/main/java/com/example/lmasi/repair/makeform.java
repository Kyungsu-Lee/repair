package com.example.lmasi.repair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lmasi.repair.*;

/**
 * Created by lmasi on 2016-05-26.
 */

public class makeform extends Activity implements  View.OnTouchListener {
    RelativeLayout main_R;
    Drawable[] drawable;
    ImageView[] imgV;
    RelativeLayout.LayoutParams[] params;

    DrawingView[] DW;

    EditText ET;

    int draw_num ;      //initialize drawable numbers
    int imgV_num ;
    int param_num ;

    int drawView_num;


    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        draw_num = imgV_num = param_num = 20;      //initialize drawable numbers

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

        //search bar
        imgV[8] = new ImageView(this);
        imgV[8].setBackgroundColor(Color.rgb(129,160,205));
        params[8] = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 183);
        params[8].setMargins(0,248,0,0);
        imgV[8].setLayoutParams(params[8]);


        for(int i=0; i < 9; i++)
        {
            main_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(this);
        }




        drawView_num = 11;
        DW = new DrawingView[drawView_num];

        for(int i=0; i<drawView_num; i++)
            DW[i] = new DrawingView(this);

        show_content();

        ET = new EditText(this);
        ET.setBackgroundColor(Color.parseColor("#FFFFFF"));
        RelativeLayout.LayoutParams paramET = new RelativeLayout.LayoutParams(1378,125);
        paramET.setMargins(29,280,0,0);
        ET.setLayoutParams(paramET);
        ET.setText("카테고리 검색");
        ET.setTextColor(Color.rgb(200,201,202));
        ET.setOnFocusChangeListener(mfocus);
        ET.setPadding(40,0,0,0);
        ET.addTextChangedListener(TW);
        main_R.addView(ET);

        main_R.setFocusable(true);
        main_R.setFocusableInTouchMode(true);

        bringViewfront();

    }

    private void bringViewfront()
    {
        for(int i=4; i<8; i++)
           imgV[i].bringToFront();


        ET.bringToFront();
    }

    private void create_phone()
    {
       // DW[0] = new DrawingView(this);
        DW[0].setColorformARGB(255,129,160,205);
        DW[0].setPosition(183,560,187);
        main_R.addView(DW[0]);
        imgV[9] = new ImageView(this);
        drawable[9] = getResources().getDrawable(R.drawable.phone);
        imgV[9].setBackground(drawable[9]);
        params[9] = new RelativeLayout.LayoutParams(103, 249);
        params[9].setMargins(230,609,0,0);
        imgV[9].setLayoutParams(params[9]);
        imgV[9].setOnTouchListener(this);
        main_R.addView(imgV[9]);
    }

    private void create_computer()
    {
     //   DW[1] = new DrawingView(this);
        DW[1].setColorformARGB(255,129,160,205);
        DW[1].setPosition(477,733,187);
        main_R.addView(DW[1]);


        imgV[11] = new ImageView(this);
        drawable[11] = getResources().getDrawable(R.drawable.computer);
        imgV[11].setBackground(drawable[11]);
        params[11] = new RelativeLayout.LayoutParams(188, 207);
        params[11].setMargins(477,823,0,0);
        imgV[11].setLayoutParams(params[11]);
        imgV[11].setOnTouchListener(this);
        main_R.addView(imgV[11]);
    }

    private void create_clothes()
    {
      //  DW[2] = new DrawingView(this);
        DW[2].setColorformARGB(255,129,160,205);
        DW[2].setPosition(477,1070,187);
        main_R.addView(DW[2]);

        imgV[10] = new ImageView(this);
        drawable[10] = getResources().getDrawable(R.drawable.clothes);
        imgV[10].setBackground(drawable[10]);
        params[10] = new RelativeLayout.LayoutParams(206, 236);
        params[10].setMargins(468,1134,0,0);
        imgV[10].setLayoutParams(params[10]);
        imgV[10].setOnTouchListener(this);
        main_R.addView(imgV[10]);
    }

    private void create_bicycle()
    {
      //  DW[3] = new DrawingView(this);
        DW[3].setColorformARGB(255,129,160,205);
        DW[3].setPosition(183,1243,187);
        main_R.addView(DW[3]);

        imgV[12] = new ImageView(this);
        drawable[12] = getResources().getDrawable(R.drawable.bicycle);
        imgV[12].setBackground(drawable[12]);
        params[12] = new RelativeLayout.LayoutParams(197, 251);
        params[12].setMargins(178,1293,0,0);
        imgV[12].setLayoutParams(params[12]);
        imgV[12].setOnTouchListener(this);
        main_R.addView(imgV[12]);
    }

    private void create_etc()
    {
    //    DW[4] = new DrawingView(this);
        DW[4].setColorformARGB(255,129,160,205);
        DW[4].setPosition(183,1578,187);
        main_R.addView(DW[4]);

        imgV[13] = new ImageView(this);
        drawable[13] = getResources().getDrawable(R.drawable.etc);
        imgV[13].setBackground(drawable[13]);
        params[13] = new RelativeLayout.LayoutParams(176, 266);
        params[13].setMargins(192,1623,0,0);
        imgV[13].setLayoutParams(params[13]);
        imgV[13].setOnTouchListener(this);
        main_R.addView(imgV[13]);
    }

    private void create_camera()
    {
     //   DW[5] = new DrawingView(this);
        DW[5].setColorformARGB(255,129,160,205);
        DW[5].setPosition(475,1748,187);
        main_R.addView(DW[5]);

        imgV[14] = new ImageView(this);
        drawable[14] = getResources().getDrawable(R.drawable.camera);
        imgV[14].setBackground(drawable[14]);
        params[14] = new RelativeLayout.LayoutParams(178, 234);
        params[14].setMargins(479,1818,0,0);
        imgV[14].setLayoutParams(params[14]);
        imgV[14].setOnTouchListener(this);
        main_R.addView(imgV[14]);
    }

    private void create_instrument()
    {
     //   DW[6] = new DrawingView(this);
        DW[6].setColorformARGB(255,129,160,205);
        DW[6].setPosition(1066,1072,187);
        main_R.addView(DW[6]);

        imgV[15] = new ImageView(this);
        drawable[15] = getResources().getDrawable(R.drawable.instrument);
        imgV[15].setBackground(drawable[15]);
        params[15] = new RelativeLayout.LayoutParams(191, 230);
        params[15].setMargins(769,1655,0,0);
        imgV[15].setLayoutParams(params[15]);
        imgV[15].setOnTouchListener(this);
        main_R.addView(imgV[15]);
    }

    private void create_fashion()
    {
      //  DW[7] = new DrawingView(this);
        DW[7].setColorformARGB(255,129,160,205);
        DW[7].setPosition(771,1242,187);
        main_R.addView(DW[7]);
    }

    private void create_machine()
    {
        //  DW[7] = new DrawingView(this);
        DW[8].setColorformARGB(255,129,160,205);
        DW[8].setPosition(771,1578,187);
        main_R.addView(DW[8]);
    }

    private void create_rux()
    {
        //  DW[7] = new DrawingView(this);
        DW[9].setColorformARGB(255,129,160,205);
        DW[9].setPosition(771,561,187);
        main_R.addView(DW[9]);
    }

    private void create_home()
    {
        //  DW[7] = new DrawingView(this);
        DW[10].setColorformARGB(255,129,160,205);
        DW[10].setPosition(1066,1748,187);
        main_R.addView(DW[10]);
    }

    private void show_content()
    {
        create_camera();
        create_instrument();
        create_bicycle();
        create_phone();
        create_fashion();
        create_clothes();
        create_computer();
        create_etc();create_home();create_machine();create_rux();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if ( v == (View)imgV[4]) {
                Intent intent = new Intent(getApplicationContext()
                        , mainpage.class);
                startActivity(intent);

                finish();
            }

            else if(v == (View)imgV[5])
            {
                finish();
            }

            else if (v == (View)imgV[6])
            {
                Intent intent = new Intent(getApplicationContext()
                        , MainActivity.class);
                startActivity(intent);

                finish();
            }

            else if (v == (View)imgV[9])
            {
                Intent intent = new Intent(getApplicationContext(), phone.class);
                startActivity(intent);

                finish();
            }

        }


        return true;
    }

    public class DrawingView extends View{

        public Paint paint;

        int X;
        int Y;
        int a;

        public DrawingView(Context context) {
            super(context);
            paint = new Paint();
           // invalidate();
        }

        public void setPosition(int X, int Y, int a)
        {
            this.X = X;
            this.Y = Y;
            this.a = a;
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
            Path path = new Path();

            path.moveTo(X,Y);
            path.lineTo(X+a,Y);
            path.lineTo((float)(X+a+a*Math.cos(Math.PI/3)),(float)(Y+a*Math.sin(Math.PI/3)));
            path.lineTo(X+a,(float)(Y+2*a*Math.sin(Math.PI/3)));
            path.lineTo(X, (float)(Y+2*a*Math.sin(Math.PI/3)));
            path.lineTo((float)(X-a*Math.cos(Math.PI/3)),(float)(Y+a*Math.sin(Math.PI/3)) );
            path.close();

            canvas.drawPath(path,paint);
        }


    }

    private TextWatcher TW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            for(int i=0; i<11; i++)
               main_R.removeView(DW[i]);
        }

        @Override
        public void afterTextChanged(Editable s) {

            for(int i=0; i<11; i++)


                main_R.removeView(DW[i]);

            if(s.toString().equals("핸드폰") || s.toString().equals("아이폰") || s.toString().equals("갤럭시"))
                create_phone();

             else if(s.toString().equals("카메라"))
            {
                create_camera();
            }

            else if(s.toString().equals("컴퓨터") || s.toString().equals("노트북") || s.toString().equals("컴퓨터") || s.toString().equals("컴퓨터") || s.toString().equals("컴퓨터"))
            {
                create_computer();
            }

            else if(s.toString().equals("신발") || s.toString().equals("옷") || s.toString().equals("셔트") || s.toString().equals("바지") || s.toString().equals("패딩"))
            {
                create_clothes();
            }

            else if(s.toString().equals("자전거"))
            {
                create_bicycle();
            }

            else if(s.toString().equals(""))
            {
                show_content();
            }

            bringViewfront();

        }
    };

    private View.OnFocusChangeListener mfocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(v == (View)ET)
            {
                ET.setTextColor(Color.BLACK);
                ET.setText("");
            }

        }
    };
}
