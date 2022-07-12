package com.basicbankingmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BANKDB";
    public static final int DATABASE_VERSON = 1;


    // Table 1 For The user Data
    private static final String TABLE_NAME="bankusers";
    private static final String KEY_NAME = "username";
    private static final String KEY_BALANCE = "userbalance";
    private static final String KEY_ACCOUNT = "useraccount";
    private static final String KEY_MOBILE = "usermobile";

    //Table2 for Transaction History
    private static final String TABLE_HISTORY_NAME = "history";
    private static final String KEY_TIME = "time";
    private static final String KEY_LASTAMOUNT = "historybalance";
    private static final String KEY_USER1 = "historyuser1";
    private static final String KEY_USER2 = "historyuser2";
    private static final String KEY_SUCESS = "issucess";




    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ACCOUNT + " INTEGER PRIMARY KEY ,"
                + KEY_NAME + " TEXT ,"
                + KEY_MOBILE + " TEXT ,"
                + KEY_BALANCE + " TEXT "
                + ")"
        );
        db.execSQL("CREATE TABLE " + TABLE_HISTORY_NAME +
                "("+ KEY_TIME + " TEXT ,"
                + KEY_USER1 + " TEXT ,"
                + KEY_USER2 + " TEXT ,"
                + KEY_SUCESS + " TEXT ,"
                + KEY_LASTAMOUNT + " TEXT "+")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_HISTORY_NAME);

    }
    public void addHistory(String time , String user1 , String user2 , String amount , String sucess){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_TIME , time);
        value.put(KEY_USER1 , user1);
        value.put(KEY_USER2 , user2);
        value.put(KEY_SUCESS , sucess);
        value.put(KEY_LASTAMOUNT , amount);
        db.insert(TABLE_HISTORY_NAME , null , value);
    }
    public  void addContacts(String name , String account , String balance,String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_NAME , name);
        value.put(KEY_ACCOUNT , account);
        value.put(KEY_BALANCE , balance);
        value.put(KEY_MOBILE , mobile);
        db.insert(TABLE_NAME , null , value);

    }
    public Cursor readHistory(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_HISTORY_NAME, null);

        return cursor;
    }
    public Cursor readAllData(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        return cursor;
    }
    public Cursor readselectuserdata(String accountnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+"  where "+KEY_ACCOUNT+" is not " +accountnumber, null);
        return cursor;
    }
    public Cursor readOneData(String account){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+"  where "+KEY_ACCOUNT+" = " +account, null);
        return cursor;
    }
    public void dataUpdate(String account1 , String account2 , String amount){
        //Money transfered from account1 to account 2
        Log.d("DATABASE","A/C No : "+account1+"A/C No. "+account2);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 =db.rawQuery("Select * from "+TABLE_NAME+" where "+KEY_ACCOUNT+" = "+account1,null);
        Cursor cursor2 =db.rawQuery("Select * from "+TABLE_NAME+" where "+KEY_ACCOUNT+" = "+account2,null);
        String money1="",money2="";
        while (cursor1.moveToNext()){
             money1=cursor1.getString(3);
        }
        while (cursor2.moveToNext()){
             money2=cursor2.getString(3);
        }

        Log.d("DATABASE","Money 1 : "+money1.toString()+"Money 2: "+money2.toString());
        String m1,m2;
        m1=(Integer.parseInt(money1)-Integer.parseInt(amount))+"";
        m2=(Integer.parseInt(money2)+Integer.parseInt(amount))+"";
        Log.d("DATABASE","Left Amount 1 :"+m1+" Received amount "+m2);

        db.execSQL("Update "+TABLE_NAME+" SET "+KEY_BALANCE+" = "+m1+" WHERE "+KEY_ACCOUNT+" = "+account1);
        db.execSQL("Update "+TABLE_NAME+" SET "+KEY_BALANCE+" = "+m2+" WHERE "+KEY_ACCOUNT+" = "+account2);
//        money1 -= Integer.parseInt(amount);
//        money2 += Integer.parseInt(amount);
//        String m1=String.valueOf(money1);
//        String m2=String.valueOf(money2);
//        Log.d("DATABASE","Money 1: "+m1+"Money 2:  "+m2);
//
//        //UPDATE employees SET lastname = 'Smith' WHERE employeeid = 3;
//
//        db.execSQL("update "+KEY_BALANCE+" set "+KEY_BALANCE+" = "+m1 +"  where "+KEY_ACCOUNT+" is " +account1, null);
//        db.execSQL("update "+KEY_BALANCE+" set "+KEY_BALANCE+" = "+m2 +"  where "+KEY_ACCOUNT+" is " +account2, null);


    }

}
