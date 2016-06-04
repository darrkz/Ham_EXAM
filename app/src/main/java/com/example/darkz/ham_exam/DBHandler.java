package com.example.darkz.ham_exam;

/**
 * Created by darkz on 2016/5/15.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    //TODO:打包apk时将sqlite数据文件打包进去
    //方法1.将assert目录中的db文件复制到存储空间
    //参考：http://www.iteedu.com/handset/android/sqlitediary/simpledbtoapk.php
    private static final String DATABASE_NAME = "ham_test_data.db";

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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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