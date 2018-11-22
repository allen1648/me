package com.stan.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import com.stan.core.utils.FileUtil;

public class DbHelper extends SQLiteOpenHelper {
    public static final String AUTHORITY = "com.stan.core.database";
    private static final int DB_VERSION = 1;

    public static final String TABLE_RAW = "TABLE_RAW";
    /** 原始的SQL查询语句的Uri，以便在query方法中进行判断 */
    public static final Uri RAW_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_RAW);
    private static String sDbName;
    private static DbHelper sDbInstance;

    public synchronized static DbHelper getInstance(Context context) {
        if (sDbInstance == null) {
            sDbInstance = new DbHelper(context);
        }

        return sDbInstance;
    }
    
    private DbHelper(Context context) {
        //在data/data外面创建数据库文件，需要绝对路径
        //super(context, sDbName, null, DB_VERSION_NEW);
        //在data/data里面创建数据库文件，只需文件名，具体看ContextImpl.openOrCreateDatabase()
        super(context, FileUtil.NAME_PHOTO_DATABASE_FILE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            createDownloadTable(db);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建表及索引
     */
    private void createDownloadTable(SQLiteDatabase db) {
        StringBuilder buf = new StringBuilder();
        //建表
        buf.append("CREATE TABLE IF NOT EXISTS ");
        buf.append(PhotoTable.TABLE_NAME);
        buf.append(" (");
        buf.append(PhotoTable._ID);
        buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        buf.append(PhotoTable.FILE_ID);
        buf.append(" TEXT,");
        buf.append(PhotoTable.STORED_PATH);
        buf.append(" TEXT,");
        buf.append(PhotoTable.COVER);
        buf.append(" TEXT,");
        buf.append(PhotoTable.TIME_STAMP);
        buf.append(" LONG,");
        buf.append(");");

        db.execSQL(buf.toString());

        //建索引
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS book_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.BOOK_ID);
//        buf.append(");");
//        db.execSQL(buf.toString());
//
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS file_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.FILE_ID);
//        buf.append(");");
//        db.execSQL(buf.toString());
//
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS time_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.TIME_STAMP);
//        buf.append(");");
//        db.execSQL(buf.toString());
//
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS file_name_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.FILE_NAME);
//        buf.append(");");
//        db.execSQL(buf.toString());
//
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS stat_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.STATE);
//        buf.append(");");
//        db.execSQL(buf.toString());
//
//        buf.delete(0, buf.length());
//        buf.append("CREATE INDEX IF NOT EXISTS sort_idx ON ");
//        buf.append(PhotoTable.TABLE_NAME);
//        buf.append("(");
//        buf.append(PhotoTable.ORDERID);
//        buf.append(");");
//        db.execSQL(buf.toString());
    }

    /********************************************************
     * 
     * photo数据表
     *
     ********************************************************/
    public static final class PhotoTable implements BaseColumns {
        /** 存放下载记录表的名称 */
        public static final String TABLE_NAME = "download";
        public static final Uri PHOTO_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
        public static final String FILE_ID = "fileId"; //2
        public static final String STORED_PATH = "storedPath";//8
        public static final String COVER = "cover"; //11
        public static final String TIME_STAMP = "timeStamp";//13
        private PhotoTable() {

        }
    }
}
