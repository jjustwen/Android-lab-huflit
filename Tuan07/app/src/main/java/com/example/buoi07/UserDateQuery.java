package com.example.buoi07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDateQuery {

    // thêm mới 1 user
    public static long insert(Context context, User us) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_USER_DEPARTID, us.departid);
        values.put(Utils.COLUMN_USER_NAME, us.name);
        values.put(Utils.COLUMN_USER_AVATAR, us.avatar);
        long rs = sqLiteDatabase.insert(Utils.TABLE_USER, null, values);
        return rs;
    }

    // lấy danh sách
    public static ArrayList<User> getAll(Context context) {
        ArrayList<User> lstUser = new ArrayList<>();
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        String strReadUser = "SELECT us.*, de.name AS departname FROM user us LEFT JOIN department de ON us.departid = de.id";
        Cursor cs = db.rawQuery(strReadUser, null);

        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String avatar = cs.getString(2);
            User item = new User(id, name, avatar);
            item.departid = cs.getInt(3);
            item.departname = cs.getString(4);
            lstUser.add(item);
            cs.moveToNext();
        }

        cs.close();
        db.close();
        return lstUser;
    }

    // xoa item
    public static boolean delete(Context context, int id) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(Utils.TABLE_USER, Utils.COLUMN_USER_ID + "=?", new String[]{String.valueOf(id)});
        return (rs > 0);
    }

    // cập nhật
    public static int update(Context context, User us) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_USER_NAME, us.getName());
        values.put(Utils.COLUMN_USER_AVATAR, us.getAvatar());
        values.put(Utils.COLUMN_USER_DEPARTID, us.getDepartid());
        int rs = sqLiteDatabase.update(Utils.TABLE_USER, values, Utils.COLUMN_USER_ID + "=?", new String[]{String.valueOf(us.getId())});
        return rs;
    }
}
