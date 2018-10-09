package com.example.darkz.ham_exam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.orhanobut.logger.Logger;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AssetDB extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "ham_test_data.db";
    private static final int DATABASE_VERSION = 1;


    public AssetDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ham_question getQuestion(String id) {
        ham_question question =  new ham_question();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
//            注意这里一定是要sdk16版本以上
            Cursor cursor = db.rawQuery("select question_id,question_level,question_content,answer_a,answer_b,answer_c,answer_d,picture_name from type_B where question_id like ?", new String[]{id + "%"}, null);
            if (cursor != null)
                cursor.moveToFirst();
            question = new ham_question(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String msg=sw.toString();
            Logger.t("db_query_error").e("db_query_error" + msg);
        }
        return question;
    }
}
