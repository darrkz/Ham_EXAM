package com.example.darkz.ham_exam;

/**
 * Created by darkz on 2016/5/15.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    //TODO:打包apk时将sqlite数据文件打包进去
    //方法1.将assert目录中的db文件复制到存储空间
    //参考: http://www.iteedu.com/handset/android/sqlitediary/simpledbtoapk.php
    //https://libuchao.com/2012/03/09/embed-sqlite-database-in-the-apk-of-android-distributed-application


    //获取一个对象所在的包名
    Date date = new Date();
    Package pack = date.getClass().getPackage();
    String packName = pack.getName();

    private  String DATABASE_NAME = "ham_test_data.db";
    private  String DATABASE_DIR  = "/data/data/"+packName+"/databases/";
    private  String DATABASE_PATH = DATABASE_DIR + DATABASE_NAME;
    File db_file= new File(DATABASE_PATH);
    File db_dir = new File(DATABASE_DIR);

    if ((new File(DATABASE_PATH)).exists() is false) {
        // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
        // 如 database 目录不存在，新建该目录
        if (!db_dir.exists()) {
            f.mkdir();
        }


        try {
            // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
            InputStream is = getBaseContext().getAssets().open(DATABASE_NAME);
            // 输出流
            OutputStream os = new FileOutputStream(DATABASE_PATH);

            // 文件写入
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            // 关闭文件流
            os.flush();
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private static final String DATABASE_NAME1 = "ham_test_data.db";

    // Contacts table name
    private static final String TABLE_SHOPS = "type_B";

    // Shops Table Columns names
    private static final String KEY_ID        = "question_id";
    private static final String KEY_LEVEL    = "question_level";
    private static final String KEY_CONTENT  = "question_content";
    private static final String KEY_ANSWER_A = "answer_a";
    private static final String KEY_ANSWER_B = "answer_b";
    private static final String KEY_ANSWER_C = "answer_c";
    private static final String KEY_ANSWER_D = "answer_d";
    private static final String KEY_PICTURE  = "picture_name";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SHOPS + "("
        //        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        //        + KEY_SH_ADDR + " TEXT" + ")";
        //db.execSQL(CREATE_CONTACTS_TABLE);
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
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_SHOPS, new String[] { KEY_ID,
        //                KEY_NAME, KEY_SH_ADDR }, KEY_ID + " like '" + id + "%'",
        //       new String[] { String.valueOf(id) }, null, null, null, null);
        //Cursor cursor = db.query("type_B", new String[] { "question_id",
        //                "question_content", "answer_a" }, "question_id like 'LK0001%'",
        //        new String[] { String.valueOf(id) }, null, null, null, null);
        Cursor cursor = db.rawQuery("select question_id,question_level,question_content,answer_a,answer_b,answer_c,answer_d,picture_name from type_B where question_id like ?",new String[]{ id + "%"},null);
        if (cursor != null)
            cursor.moveToFirst();
        ham_question question = new ham_question(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)
        );
        //String concat = (cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
        // return shop
        //return contact;
        //String question_to_show=getShop("LK0001").getAddress();
        //android.util.Log.d("test sqllite data.",question_to_show);
        //System.out.print(question_to_show);
        return question;
    }
}