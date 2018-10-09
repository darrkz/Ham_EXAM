package com.example.darkz.ham_exam;

/**
 * Created by darkz on 2016/5/15.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ham_test_data.db";

    private static final String KEY_ID       = "question_id";
    private static final String KEY_LEVEL    = "question_level";
    private static final String KEY_CONTENT  = "question_content";
    private static final String KEY_ANSWER_A = "answer_a";
    private static final String KEY_ANSWER_B = "answer_b";
    private static final String KEY_ANSWER_C = "answer_c";
    private static final String KEY_ANSWER_D = "answer_d";
    private static final String KEY_PICTURE  = "picture_name";

    private final Context myContext;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }
    //@Override
    public void onCreate(SQLiteDatabase db) {
        // TODO:打包apk时将sqlite数据文件打包进去，默认assets里面的内容会被打爆到apk中去么？
        // 方法1.将assert目录中的db文件复制到存储空间
        // 参考: http://www.iteedu.com/handset/android/sqlitediary/simpledbtoapk.php
        //      https://libuchao.com/2012/03/09/embed-sqlite-database-in-the-apk-of-android-distributed-application

        // 自动获取当前包名,在main_activity中获取，在这里调用
        String packName1 = MainActivity.PACKAGE_NAME;
        String DATABASE_DIR  = "/data/data/"+packName1+"/databases/";
        String DATABASE_PATH = DATABASE_DIR + DATABASE_NAME ;
        File db_dir_name = new File(DATABASE_DIR);
        File db_file_name = new File(DATABASE_PATH);
        File inputFile = new File("databases/" + DATABASE_NAME);

        Logger.t("dbcopy").e("copy_db_read" + "size of" + DATABASE_PATH + "is" +db_file_name.length());
        Logger.t("dbcopy").e("copy_db_read" + "size of" + DATABASE_PATH + "is" +db_file_name.hashCode());
        Logger.t("dbcopy").e("copy_db_read_input" + "size of" + inputFile + "is" + inputFile.length());
        Logger.t("dbcopy").e("copy_db_read_input" + "size of" + inputFile + "is" + inputFile.hashCode());

        if (!db_file_name.exists()) {
        // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
        // 如 database 目录不存在，新建该目录
            if (!db_dir_name.exists() || !db_dir_name.isDirectory() )
            {
                db_dir_name.mkdir();
            }

            try {
                // 得到 assets 目录下我们事先准备好的 SQLite 数据库作为输入流

                InputStream is = myContext.getAssets().open("databases/" + DATABASE_NAME);
                //InputStream is = getBaseContext().getAssets().open("databases/" + DATABASE_NAME);

                // 输出流
                OutputStream os = new FileOutputStream(DATABASE_PATH);

                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

            } catch (Exception e) {
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String msg=sw.toString();
                String error_msg= "没法玩？出错了！！！" + packName1 + msg;
                Logger.t("dbcopy").e("copy_db_error",error_msg);
                Logger.t("dbcopy").e("copy_db_error" + "could not open" + DATABASE_PATH);
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPS);
        // Creating tables again
        //onCreate(db);
    }
    // Getting one shop
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