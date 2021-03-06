package com.androidag.sqlitelogin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androidag.sqlitelogin.model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_database";
    private static final String TB_NAME = "user_table";
    private static final String COL_ID = "user_id";
    private static final String COL_FIRST_NAME = "user_first_name";
    private static final String COL_LAST_NAME = "user_last_name";
    private static final String COL_LAST_NAME_TWO = "user_last_name_two";
    private static final String COL_USERNAME = "user_username";
    //private static final String COL_MOBILE_NUMBER = "user_mobile_number";
    //private static final String COL_EMAIL = "user_email";
    private static final String COL_PASSWORD = "user_password";
    private static final String COL_CONFIRM_PASSWORD = "user_confirm_password";
    private static final String COL_GENDER = "user_gender";
    private static final String COL_BIRTHDATE = "user_birthdate";

    private static final int VERSION = 3;
    private SQLiteDatabase sqLiteDatabase;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_user_table = "CREATE TABLE "+TB_NAME+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_FIRST_NAME+" TEXT,"
                +COL_LAST_NAME+" TEXT,"
                +COL_LAST_NAME_TWO+" TEXT,"
                +COL_USERNAME+" TEXT,"
              //  +COL_MOBILE_NUMBER+" TEXT,"
              //  +COL_EMAIL+" TEXT,"
                +COL_PASSWORD+" TEXT,"
                +COL_CONFIRM_PASSWORD+" TEXT,"
                +COL_GENDER+" TEXT,"
                +COL_BIRTHDATE+" TEXT)";
        sqLiteDatabase.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String drop_user_table = "DROP TABLE IF EXISTS "+TB_NAME;
        sqLiteDatabase.execSQL(drop_user_table);

        onCreate(sqLiteDatabase);
    }

    public void onInsert(User user)
    {
        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME,user.getFirst_name());
        values.put(COL_LAST_NAME,user.getLast_name());
        values.put(COL_LAST_NAME_TWO,user.getLast_name_two());
        values.put(COL_USERNAME,user.getUsername());
        //values.put(COL_MOBILE_NUMBER,user.getMobile_number());
        //values.put(COL_EMAIL,user.getEmail());
        values.put(COL_PASSWORD,user.getPassword());
        values.put(COL_CONFIRM_PASSWORD,user.getConfirm_password());
        values.put(COL_GENDER,user.getGender());
        values.put(COL_BIRTHDATE,user.getBirthdate());

        sqLiteDatabase.insert(TB_NAME,null,values);
        sqLiteDatabase.close();
    }
    public ArrayList<User> onDisplay() {

        sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TB_NAME, null);

        ArrayList<User> list = new ArrayList<>();
        while (c != null && c.moveToNext()) {

            User user = new User();
            user.setId(c.getInt(0));
            user.setFirst_name(c.getString(1));
            user.setLast_name(c.getString(2));
            user.setLast_name_two(c.getString(3));
            user.setUsername(c.getString(4));
            //user.setMobile_number(c.getString(3));
            //user.setEmail(c.getString(4));
            user.setPassword(c.getString(5));
            user.setConfirm_password(c.getString(6));
            user.setGender(c.getString(7));
            user.setBirthdate(c.getString(8));

            Log.d("SQLIte", user.getFirst_name());
            list.add(user);
        }


        return list;
    }
    public int onDelete(User user)
    {

        sqLiteDatabase = getWritableDatabase();
        int delete_sql = sqLiteDatabase.delete(TB_NAME,COL_ID +"= ?",new String[]{String.valueOf(user.getId())});
        return delete_sql;

    }
    public int onUpdate(User user)
    {

        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_FIRST_NAME,user.getFirst_name());
        values.put(COL_LAST_NAME,user.getLast_name());
        values.put(COL_LAST_NAME_TWO,user.getLast_name_two());
        values.put(COL_USERNAME,user.getUsername());
        //values.put(COL_MOBILE_NUMBER,user.getMobile_number());
        //values.put(COL_EMAIL,user.getEmail());
        values.put(COL_PASSWORD,user.getPassword());
        values.put(COL_CONFIRM_PASSWORD,user.getConfirm_password());
        values.put(COL_GENDER,user.getGender());
        values.put(COL_BIRTHDATE,user.getBirthdate());

        int update_sql = sqLiteDatabase.update(TB_NAME,values,COL_ID +" = ?",new String[]{String.valueOf(user.getId())});

        return update_sql;
    }
/*
    public boolean isEmailExist(String email)
    {
        boolean exist = false;
        sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+COL_EMAIL+" = '" +email + "'",null);
        while (c.moveToNext())
        {
            if (c.getString(1)!=null)
            {
                exist = true;
            }
            else
            {
                exist = false;
            }
        }

        return exist;
    }

    public boolean isMobileNumberExist(String mobileNumber)
    {
        boolean exist = false;
        sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+COL_MOBILE_NUMBER+" = '" +mobileNumber + "'",null);
        while (c.moveToNext())
        {
            if (c.getString(1)!=null)
            {
                exist = true;
            }
            else
            {
                exist = false;
            }
        }

        return exist;
    } */

    public boolean isUsernameExist(String username)
    {
        boolean exist = false;
        sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+COL_USERNAME+" = '" +username + "'",null);
        while (c.moveToNext())
        {
            if (c.getString(1)!=null)
            {
                exist = true;
            }
            else
            {
                exist = false;
            }
        }

        return exist;
    }

    public boolean loginValidation(String username,String password)
    {
        boolean valid = false;
        sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+COL_USERNAME+" = '" +username + "' and "+COL_PASSWORD+" = '" +password+"' " ,null);
        while (c.moveToNext())
        {
            if (c.getString(1)!=null)
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
        }

        return valid;
    }
    /*
    public boolean loginValidationMobile(String mobile_number,String password)
    {
        boolean valid = false;
        sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+COL_MOBILE_NUMBER+" = '" +mobile_number + "' and "+COL_PASSWORD+" = '" +password+"' " ,null);
        while (c.moveToNext())
        {
            if (c.getString(1)!=null)
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
        }

        return valid;
    }
*/
}

