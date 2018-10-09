package com.example.darkz.ham_exam;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //获取package的名称
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.t("试试了").i("什么玩意");
//        Logger.log("warn","调试", "就试试了");
//        Logger.init("test");

        PACKAGE_NAME = getApplicationContext().getPackageName();


        //TextView question_id      = (TextView) findViewById(R.id.question_content_textview);
        //TextView question_level   = (TextView) findViewById(R.id.question_level);
        TextView question_content       = (TextView) findViewById(R.id.question_content_textview);
        TextView answer_a               = (TextView) findViewById(R.id.answer_a_textView);
        TextView answer_b               = (TextView) findViewById(R.id.answer_b_textView);
        TextView answer_c               = (TextView) findViewById(R.id.answer_c_textView);
        TextView answer_d               = (TextView) findViewById(R.id.answer_d_textView);
        TextView picture_name_textView  = (TextView) findViewById(R.id.picturetextView);
        //TextView testView               = (TextView) findViewById(R.id.testView);
        //Button prvButton                = (Button) findViewById(R.id.prvButton);
        //Button submitButton             = (Button) findViewById(R.id.submitButton);
        //Button nextButton               = (Button) findViewById(R.id.nexButton);
        ImageView question_picture      = (ImageView) findViewById(R.id.picture_name);



        // 从sqllite 中读取信息
        DBHandler db_test = new DBHandler(this);

        String question_id_to_query="LK0497";
        //String question_id_to_query="LK0001";
        question_content.setText("[Q]:"+db_test.getQuestion(question_id_to_query).getQuestion_content());
        answer_a.setText("[A]:"+db_test.getQuestion(question_id_to_query).getAnswer_a());
        answer_b.setText("[B]:"+db_test.getQuestion(question_id_to_query).getAnswer_b());
        answer_c.setText("[C]:"+db_test.getQuestion(question_id_to_query).getAnswer_c());
        answer_d.setText("[D]:"+db_test.getQuestion(question_id_to_query).getAnswer_d());
        String picture_name_text=db_test.getQuestion(question_id_to_query).getPicture_name();
        picture_name_textView.setText("图片名称:" + picture_name_text);

        //从app内部的assert目录加载图片
        //注意assert目录的位置
        // load image
        try {
            // get input stream
            InputStream ims = getAssets().open("pic/" + picture_name_text);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            question_picture.setImageDrawable(d);
        }
        catch(IOException ex) {
            //return;
            ex.printStackTrace();
        }
        //是否显示图片


    }
    public void submitQuestion(View view) {
        TextView testView = (TextView)findViewById(R.id.testView);
        testView.setText("你点击了Button");
        //TODO: 根据时间点击更换activity的内容
        //http://blog.csdn.net/pku_android/article/details/7310877
    }
}
