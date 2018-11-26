package com.stan.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "me.db";
    private static final int DB_VERSION = 1;

    protected DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            createPhotoTable(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createPhotoTable(SQLiteDatabase db) {
        StringBuilder buf = new StringBuilder();
        //建表
        buf.append("CREATE TABLE IF NOT EXISTS ");
        buf.append(PhotoTable.TABLE_NAME);
        buf.append(" (");
        buf.append(PhotoTable._ID);
        buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        buf.append(PhotoTable.STORED_PATH);
        buf.append(" TEXT,");
        buf.append(PhotoTable.URI);
        buf.append(" TEXT,");
        buf.append(PhotoTable.DATE);
        buf.append(" TEXT,");
        buf.append(PhotoTable.SIZE);
        buf.append(" INTEGER,");
        buf.append(PhotoTable.TITLE);
        buf.append(" TEXT,");
        buf.append(PhotoTable.CONTENT);
        buf.append(" TEXT,");
        buf.append(PhotoTable.SCALE);
        buf.append(" FLOAT(6,2)");
        buf.append(");");
        db.execSQL(buf.toString());
    }


    /********************************************************
     *
     * photo数据表
     *
     ********************************************************/
    public static final class PhotoTable implements BaseColumns {
        public static final String TABLE_NAME = "photo";
        public static final String STORED_PATH = "storedPath";
        public static final String URI = "uri";
        public static final String DATE = "date";
        public static final String SIZE = "size";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String SCALE = "scale";

        private PhotoTable() {

        }
    }
}
