package com.example.darkz.ham_exam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView question_id      = (TextView) findViewById(R.id.question_content_textview);
        //TextView question_level   = (TextView) findViewById(R.id.question_level);
        TextView question_content = (TextView) findViewById(R.id.question_content_textview);
        TextView answer_a         = (TextView) findViewById(R.id.answer_a_textView);
        TextView answer_b         = (TextView) findViewById(R.id.answer_b_textView);
        TextView answer_c         = (TextView) findViewById(R.id.answer_c_textView);
        TextView answer_d         = (TextView) findViewById(R.id.answer_d_textView);
        TextView picture_name_text= (TextView) findViewById(R.id.picturetextView);

        ImageView picture_name    = (ImageView) findViewById(R.id.picture_name);

        // 从sqllite 中读取信息
        DBHandler db_test = new DBHandler(this);
        question_content.setText("[Q]:"+db_test.getQuestion("LK0001").getQuestion_content());
        TextPaint tp = question_content.getPaint();
        tp.setTextSize(60);
        tp.setFakeBoldText(true);
        //question_content.setFontFeatureSettings();
        //question_content.setText(db_test.getQuestion("LK0001").getQuestion_content());
        String question_id_to_query="LK0497";
        //String question_id_to_query="LK0001";
        answer_a.setText("[A]:"+db_test.getQuestion(question_id_to_query).getAnswer_a());
        answer_b.setText("[B]:"+db_test.getQuestion(question_id_to_query).getAnswer_b());
        answer_c.setText("[C]:"+db_test.getQuestion(question_id_to_query).getAnswer_c());
        answer_d.setText("[D]:"+db_test.getQuestion(question_id_to_query).getAnswer_d());
        picture_name_text.setText("图片名称:"+db_test.getQuestion(question_id_to_query).getPicture_name());

//        if (db_test.getQuestion(question_id_to_query).getPicture_name().length() != 0)
            String file_path = "pic/" + db_test.getQuestion(question_id_to_query).getPicture_name();
            File file = new File(file_path);
            if (file.exists()) {
                Bitmap bm = BitmapFactory.decodeFile(file_path);
                //将图片显示到ImageView中
                picture_name.setImageBitmap(bm);
            }
//        //是否显示图片

    }
}
