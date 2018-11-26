package com.stan.core.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stan.core.MeApplication;

public abstract class BaseDao {
    protected static SQLiteDatabase mSQLiteDatabase;
    protected static DBHelper mDBHelper;

    public BaseDao() {
        if (mSQLiteDatabase == null) {
            mDBHelper = new DBHelper(MeApplication.getInstance());
            mSQLiteDatabase = mDBHelper.getWritableDatabase();
        }
    }

    public static void ensureDatabaseOpen() {
        if (mSQLiteDatabase == null) {
            mDBHelper = new DBHelper(MeApplication.getInstance());
            mSQLiteDatabase = mDBHelper.getWritableDatabase();
        }
    }

    public static void close() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
            mSQLiteDatabase = null;
        }
        if (mDBHelper != null) {
            mDBHelper.close();
            mDBHelper = null;
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return mSQLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        return mSQLiteDatabase.insert(table, nullColumnHack, values);
    }


    public int delete(String table, String whereClause, String[] whereArgs) {
        return mSQLiteDatabase.delete(table, whereClause, whereArgs);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return mSQLiteDatabase.update(table, values, whereClause, whereArgs);
    }

}
