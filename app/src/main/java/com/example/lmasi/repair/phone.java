package com.example.lmasi.repair;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by lmasi on 2016-05-27.
 */
public class phone extends Activity {

    RelativeLayout main_R;
    Drawable[] drawable;
    ImageView[] imgV;
    RelativeLayout.LayoutParams[] params;
    EditText ET;
    Boolean[] Checked;

    int draw_num;      //initialize drawable numbers
    int imgV_num;
    int param_num;

    DrawingView[] DV;


    private Handler mHandler;
    private Runnable mRunnable;

    enum Phone_Model {android, apple, none}

    Phone_Model model;

    String out_srt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);

        out_srt = "";

        draw_num = 30;      //initialize drawable numbers
        imgV_num = 30;
        param_num = 30;

        drawable = new Drawable[draw_num];
        imgV = new ImageView[imgV_num];

        params = new RelativeLayout.LayoutParams[param_num];

        DV = new DrawingView[4];

        Checked = new Boolean[8];

        for(int i=0; i<8; i++)
            Checked[i] = false;


        main_R = (RelativeLayout) findViewById(R.id.RR);

        //make_page(0,8);

        // make_form(8,12);

        model = Phone_Model.none;

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

        for (int i = 0; i < 8; i++) {
            main_R.addView(imgV[i]);
            imgV[i].setOnTouchListener(mtouch);
        }

        imgV[8] = new ImageView(this);
        drawable[8] = getResources().getDrawable(R.drawable.submit);
        imgV[8].setBackground(drawable[8]);
        params[8] = new RelativeLayout.LayoutParams(530, 144);
        params[8].setMargins(455, 2087, 0, 0);
        imgV[8].setLayoutParams(params[8]);
        imgV[8].setOnTouchListener(mtouch);
        main_R.addView(imgV[8]);

        imgV[9] = new ImageView(this);
        drawable[9] = getResources().getDrawable(R.drawable.chose_model);
        imgV[9].setBackground(drawable[9]);
        params[9] = new RelativeLayout.LayoutParams(180, 63);
        params[9].setMargins(135, 290, 0, 0);
        imgV[9].setLayoutParams(params[9]);
        imgV[9].setOnTouchListener(mtouch);
        main_R.addView(imgV[9]);

        DV[0] = new DrawingView(this);
        DV[0].setColorformARGB(255, 129, 160, 205);
        DV[0].setPosition(471, 520, 140);
        main_R.addView(DV[0]);

        DV[1] = new DrawingView(this);
        DV[1].setColorformARGB(255, 255, 255, 255);
        DV[1].setPosition(471, 520, 130);
        main_R.addView(DV[1]);

        DV[2] = new DrawingView(this);
        DV[2].setColorformARGB(255, 129, 160, 205);
        DV[2].setPosition(966, 520, 140);
        main_R.addView(DV[2]);

        DV[3] = new DrawingView(this);
        DV[3].setColorformARGB(255, 255, 255, 255);
        DV[3].setPosition(966, 520, 130);
        main_R.addView(DV[3]);


        imgV[10] = new ImageView(this);
        drawable[10] = getResources().getDrawable(R.drawable.android_model);
        imgV[10].setBackground(drawable[10]);
        params[10] = new RelativeLayout.LayoutParams(154, 183);
        params[10].setMargins(395, 429, 0, 0);
        imgV[10].setLayoutParams(params[10]);
        imgV[10].setOnTouchListener(mtouch);
        main_R.addView(imgV[10]);

        imgV[11] = new ImageView(this);
        drawable[11] = getResources().getDrawable(R.drawable.apple);
        imgV[11].setBackground(drawable[11]);
        params[11] = new RelativeLayout.LayoutParams(133, 154);
        params[11].setMargins(902, 444, 0, 0);
        imgV[11].setLayoutParams(params[11]);
        imgV[11].setOnTouchListener(mtouch);
        main_R.addView(imgV[11]);


        Line line = new Line(this);
        main_R.addView(line);

        //수리파트 설정
        imgV[12] = new ImageView(this);
        drawable[12] = getResources().getDrawable(R.drawable.set_part);
        imgV[12].setBackground(drawable[12]);
        params[12] = new RelativeLayout.LayoutParams(263, 63);
        params[12].setMargins(135, 740, 0, 0);
        imgV[12].setLayoutParams(params[12]);
        main_R.addView(imgV[12]);


        //상세내역
        imgV[13] = new ImageView(this);
        drawable[13] = getResources().getDrawable(R.drawable.details);
        imgV[13].setBackground(drawable[13]);
        params[13] = new RelativeLayout.LayoutParams(164, 63);
        params[13].setMargins(135, 1174, 0, 0);
        imgV[13].setLayoutParams(params[13]);
        main_R.addView(imgV[13]);

        //방문시간
        imgV[14] = new ImageView(this);
        drawable[14] = getResources().getDrawable(R.drawable.time);
        imgV[14].setBackground(drawable[14]);
        params[14] = new RelativeLayout.LayoutParams(266, 63);
        params[14].setMargins(135, 1811, 0, 0);
        imgV[14].setLayoutParams(params[14]);
        main_R.addView(imgV[14]);

        //액정
        imgV[15] = new ImageView(this);
        drawable[15] = getResources().getDrawable(R.drawable.phone_box_dorwjd);
        imgV[15].setBackground(drawable[15]);
        params[15] = new RelativeLayout.LayoutParams(288, 126);
        params[15].setMargins(123, 828, 0, 0);
        imgV[15].setLayoutParams(params[15]);
        main_R.addView(imgV[15]);
        imgV[15].setOnTouchListener(mtouch);

        //충전
        imgV[16] = new ImageView(this);
        drawable[16] = getResources().getDrawable(R.drawable.phone_box_cndwjs);
        imgV[16].setBackground(drawable[16]);
        params[16] = new RelativeLayout.LayoutParams(288, 126);
        params[16].setMargins(423, 828, 0, 0);
        imgV[16].setLayoutParams(params[16]);
        main_R.addView(imgV[16]);
        imgV[16].setOnTouchListener(mtouch);

        //소리
        imgV[17] = new ImageView(this);
        drawable[17] = getResources().getDrawable(R.drawable.phone_box_thfl);
        imgV[17].setBackground(drawable[17]);
        params[17] = new RelativeLayout.LayoutParams(288, 126);
        params[17].setMargins(726, 828, 0, 0);
        imgV[17].setLayoutParams(params[17]);
        main_R.addView(imgV[17]);
        imgV[17].setOnTouchListener(mtouch);

        //전원
        imgV[18] = new ImageView(this);
        drawable[18] = getResources().getDrawable(R.drawable.phone_box_wjsdnjs);
        imgV[18].setBackground(drawable[18]);
        params[18] = new RelativeLayout.LayoutParams(288, 126);
        params[18].setMargins(1029, 828, 0, 0);
        imgV[18].setLayoutParams(params[18]);
        main_R.addView(imgV[18]);
        imgV[18].setOnTouchListener(mtouch);

        //버튼
        imgV[19] = new ImageView(this);
        drawable[19] = getResources().getDrawable(R.drawable.phone_box_qjxms);
        imgV[19].setBackground(drawable[19]);
        params[19] = new RelativeLayout.LayoutParams(288, 126);
        params[19].setMargins(123, 962, 0, 0);
        imgV[19].setLayoutParams(params[19]);
        main_R.addView(imgV[19]);
        imgV[19].setOnTouchListener(mtouch);

        //잭
        imgV[20] = new ImageView(this);
        drawable[20] = getResources().getDrawable(R.drawable.phone_box_wor);
        imgV[20].setBackground(drawable[20]);
        params[20] = new RelativeLayout.LayoutParams(288, 126);
        params[20].setMargins(423, 962, 0, 0);
        imgV[20].setLayoutParams(params[20]);
        main_R.addView(imgV[20]);
        imgV[20].setOnTouchListener(mtouch);

        //통화
        imgV[21] = new ImageView(this);
        drawable[21] = getResources().getDrawable(R.drawable.phone_box_xhdghk);
        imgV[21].setBackground(drawable[21]);
        params[21] = new RelativeLayout.LayoutParams(288, 126);
        params[21].setMargins(726, 962, 0, 0);
        imgV[21].setLayoutParams(params[21]);
        main_R.addView(imgV[21]);
        imgV[21].setOnTouchListener(mtouch);

        //카메라
        imgV[22] = new ImageView(this);
        drawable[22] = getResources().getDrawable(R.drawable.phone_box_zkapfk);
        imgV[22].setBackground(drawable[22]);
        params[22] = new RelativeLayout.LayoutParams(288, 126);
        params[22].setMargins(1029, 962, 0, 0);
        imgV[22].setLayoutParams(params[22]);
        main_R.addView(imgV[22]);
        imgV[22].setOnTouchListener(mtouch);

        //상세내역 박스
        imgV[23] = new ImageView(this);
        drawable[23] = getResources().getDrawable(R.drawable.phone_box);
        imgV[23].setBackground(drawable[23]);
        params[23] = new RelativeLayout.LayoutParams(1200, 456);
        params[23].setMargins(123, 1278, 0, 0);
        imgV[23].setLayoutParams(params[23]);
        main_R.addView(imgV[23]);


        //이미지 삽입
        imgV[24] = new ImageView(this);
        drawable[24] = getResources().getDrawable(R.drawable.img);
        imgV[24].setBackground(drawable[24]);
        params[24] = new RelativeLayout.LayoutParams(229, 226);
        params[24].setMargins(995, 1387, 0, 0);
        imgV[24].setLayoutParams(params[24]);
        main_R.addView(imgV[24]);
        //imgV[24].setOnTouchListener(mtouch);
        imgV[24].setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);


            }

        });


        ET = new EditText(this);
        ET.setBackgroundColor(Color.WHITE);
        ET.setTextColor(Color.rgb(198, 198, 199));
        ET.setText("수리 받으실 내역을 상세히 입력하여주세요.");
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(681, 369);
        param.setMargins(162, 1330, 0, 0);
        ET.setOnFocusChangeListener(mfocus);
        ET.setLayoutParams(param);
        main_R.addView(ET);

        main_R.setFocusable(true);
        main_R.setFocusableInTouchMode(true);
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {


        if(requestCode == 100)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    //배치해놓은 ImageView에 set
                    imgV[24].setBackgroundColor(Color.WHITE);
                     imgV[24].setImageBitmap(image_bitmap);
                  // drawable[24] = new BitmapDrawable(image_bitmap);
                  //  drawable[24].invalidateSelf();
                   // imgV[24].invalidate();


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }



    private View.OnFocusChangeListener mfocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (v == (View) ET) {
                ET.setTextColor(Color.BLACK);
                ET.setText("");
            }

        }
    };

    class Line extends View {
        public Line(Context context) {
            super(context);
        }

        public Line(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        public Line(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Path path = new Path();
            Paint paint = new Paint();
            path.moveTo(870, 1371);
            path.lineTo(876, 1371);
            path.lineTo(876, 1628);
            path.lineTo(870, 1628);
            path.close();

            paint.setColor(Color.argb(255, 129, 160, 205));

            canvas.drawPath(path, paint);
        }


    }

    public class DrawingView extends View {

        public int X;
        public int Y;
        public int R;

        public Paint paint;


        public DrawingView(Context context) {
            super(context);
        }

        public void setPosition(int X, int Y, int R) {
            this.X = X;
            this.Y = Y;
            this.R = R;
        }

        public void setColorformARGB(int alpha, int R, int G, int B) {
            paint = new Paint();
            paint.setColor(Color.argb(alpha, R, G, B));
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

                    finish();
                } else if (v == (View) imgV[10]) {
                    if (model != Phone_Model.android) {
                        DV[1].setColorformARGB(221, 238, 200, 200);
                        DV[1].invalidate();
                        DV[3].setColorformARGB(255, 255, 255, 255);
                        DV[3].invalidate();
                        model = Phone_Model.android;
                    } else {
                        DV[1].setColorformARGB(255, 255, 255, 255);
                        DV[1].invalidate();
                        model = Phone_Model.none;
                    }


                } else if (v == (View) imgV[11]) {
                    if (model != Phone_Model.apple) {
                        DV[3].setColorformARGB(221, 238, 200, 200);
                        DV[3].invalidate();
                        DV[1].setColorformARGB(255, 255, 255, 255);
                        DV[1].invalidate();
                        model = Phone_Model.apple;
                    } else {
                        DV[3].setColorformARGB(255, 255, 255, 255);
                        DV[3].invalidate();
                        model = Phone_Model.none;
                    }


                } else if ( v == (View)imgV[8]) {
                    try {

                        String str = "";

                        File i_file = getFileStreamPath("text.txt");
                        FileInputStream fis = new FileInputStream(i_file);
                        Reader in = new InputStreamReader(fis);
                        int size = fis.available();
                        char[] buffer = new char[size];
                        in.read(buffer);
                        in.close();
                        str = new String(buffer);

                        out_srt += str;

                        if(model == Phone_Model.android)
                            out_srt += "안드로이드\n\n";
                        else if(model == Phone_Model.apple)
                            out_srt += "아이폰\n\n";
                        else
                            out_srt += "\n";

                       if(Checked[0])
                           out_srt += "액정/";

                        if(Checked[1])
                            out_srt += "충전/";

                        if(Checked[2])
                            out_srt += "소리/";

                        if(Checked[3])
                            out_srt += "전원/";

                        if(Checked[4])
                            out_srt += "버튼/";

                        if(Checked[5])
                            out_srt += "이어폰 잭/";

                        if(Checked[6])
                            out_srt += "통화/";

                        if(Checked[7])
                            out_srt += "카메라/";

                        out_srt.substring(0,out_srt.length()-2);
                        out_srt += "\n\n";


                        out_srt += ET.getText() + "$";


                        File file = getFileStreamPath("text.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        Writer out = new OutputStreamWriter(fos, "UTF-8");
                        out.write(out_srt);
                        out.close();


                        Intent intent = new Intent(getApplicationContext()
                                , mainpage.class);
                        startActivity(intent);

                        finish();
                    }

                    catch(IOException e)
                    {
                        throw  new RuntimeException(e);
                    }
                }

                else if ( v == (View)imgV[15])
                {
                    if(!Checked[0])
                    {
                       drawable[15] = getResources().getDrawable(R.drawable.phone_box_dorwjd_clicked);
                        imgV[15].setBackground(drawable[15]);
                        imgV[15].clearColorFilter();
                        drawable[15].invalidateSelf();
                        imgV[15].invalidate();
                    }

                    else
                    {
                        drawable[15] = getResources().getDrawable(R.drawable.phone_box_dorwjd);
                        imgV[15].setBackground(drawable[15]);
                        imgV[15].clearColorFilter();
                        drawable[15].invalidateSelf();
                        imgV[15].invalidate();
                    }

                    Checked[0] = !Checked[0];
                }

                else if ( v == (View)imgV[16])
                {
                    if(!Checked[1])
                    {
                        imgV[16].setImageResource(R.drawable.phone_box_cndwjs_checked);
                    }

                    else
                    {
                        imgV[16].setImageResource(R.drawable.phone_box_cndwjs);
                    }

                    Checked[1] = !Checked[1];
                }

                else if ( v == (View)imgV[17])
                {
                    if(!Checked[2])
                         imgV[17].setImageResource(R.drawable.phone_box_thfl_checked);

                    else
                        imgV[17].setImageResource(R.drawable.phone_box_thfl);

                    Checked[2] = !Checked[2];
                }

                else if ( v == (View)imgV[18])
                {
                    if(!Checked[3])
                        imgV[18].setImageResource(R.drawable.phone_box_wjsdnjs_checked);

                    else
                        imgV[18].setImageResource(R.drawable.phone_box_wjsdnjs);

                    Checked[3] = !Checked[3];
                }

                else if ( v == (View)imgV[19])
                {
                    if(!Checked[4])
                        imgV[19].setImageResource(R.drawable.phone_box_qjxms_checked);

                    else
                        imgV[19].setImageResource(R.drawable.phone_box_qjxms);

                    Checked[4] = !Checked[4];
                }

                else if ( v == (View)imgV[20])
                {
                    if(!Checked[5])
                        imgV[20].setImageResource(R.drawable.phone_box_wor);

                    else
                        imgV[20].setImageResource(R.drawable.phone_box_wor_checked);

                    Checked[5] = !Checked[5];
                }

                else if ( v == (View)imgV[21])
                {
                    if(!Checked[6])
                        imgV[21].setImageResource(R.drawable.phone_box_xhdghk_checked);

                    else
                        imgV[21].setImageResource(R.drawable.phone_box_xhdghk);

                    Checked[6] = !Checked[6];
                }

                else if ( v == (View)imgV[22])
                {
                    if(!Checked[7])
                        imgV[22].setImageResource(R.drawable.phone_box_zkapfk_checked);

                    else
                        imgV[22].setImageResource(R.drawable.phone_box_zkapfk);

                    Checked[7] = !Checked[7];
                }


            }

            return true;
        }
    };
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Uri uri;

        uri = data.getData();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        startActivityForResult(intent, 2);
    }*/

}
