package com.stan.core.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.LinkedList;
import java.util.List;

public class PhotoDao extends BaseDao {

    public List<PhotoRecord> queryPhoto(int startIndex, int endIndex) {
        ensureDatabaseOpen();
        List<PhotoRecord> list = new LinkedList<>();
        Cursor cursor = mSQLiteDatabase.query(DBHelper.PhotoTable.TABLE_NAME, null, "_id >= ? and _id < ?",
                new String[]{startIndex + "", endIndex + ""}, null, null, null);
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(DBHelper.PhotoTable.STORED_PATH));
            String uri = cursor.getString(cursor.getColumnIndex(DBHelper.PhotoTable.URI));
            int size = cursor.getInt(cursor.getColumnIndex(DBHelper.PhotoTable.SIZE));
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.PhotoTable.DATE));
            String title = cursor.getString(cursor.getColumnIndex(DBHelper.PhotoTable.TITLE));
            String content = cursor.getString(cursor.getColumnIndex(DBHelper.PhotoTable.CONTENT));
            float scale = cursor.getFloat(cursor.getColumnIndex(DBHelper.PhotoTable.SCALE));
            list.add(new PhotoRecord(path, uri, size, date, title, content, scale));
        }
        cursor.close();
        return list;
    }

    public boolean addPhoto(String photoPath, String uri, int size, String date, String title, String content, float scale) {
        ensureDatabaseOpen();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.PhotoTable.STORED_PATH, photoPath);
        cv.put(DBHelper.PhotoTable.URI, uri);
        cv.put(DBHelper.PhotoTable.SIZE, size);
        cv.put(DBHelper.PhotoTable.DATE, date);
        cv.put(DBHelper.PhotoTable.TITLE, title);
        cv.put(DBHelper.PhotoTable.CONTENT, content);
        cv.put(DBHelper.PhotoTable.SCALE, scale);
        int result = (int) mSQLiteDatabase.insert(DBHelper.PhotoTable.TABLE_NAME, null, cv);
        return result != -1;
    }

    public boolean removePhoto(String uri) {
        ensureDatabaseOpen();
        return mSQLiteDatabase.delete(DBHelper.PhotoTable.TABLE_NAME, DBHelper.PhotoTable.URI + " = ?", new String[]{uri}) != 0;
    }

    /**
     * 暂时用uri判断是否是同一张图片
     *
     * @param uri
     * @return
     */
    public boolean canAdd(String uri) {
        Cursor cursor = mSQLiteDatabase.query(DBHelper.PhotoTable.TABLE_NAME, null, "uri == ?",
                new String[]{uri}, null, null, null);
        return !cursor.moveToFirst();
    }

    public static class PhotoRecord {
        private String mPath;
        private Uri mUri;
        private int mSize;
        private String mDate;
        private String mTitle;
        private String mContent;
        private float mScale;

        public PhotoRecord(String path, String uri, int size, String date, String title, String content, float scale) {
            mPath = path;
            mUri = Uri.parse(uri);
            mSize = size;
            mDate = date;
            mTitle = title;
            mContent = content;
            mScale = scale;
        }

        public String getPath() {
            return mPath;
        }

        public Uri getUri() {
            return mUri;
        }

        public int getSize() {
            return mSize;
        }

        public String getDate() {
            return mDate;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getContent() {
            return mContent;
        }

        public float getScale() {
            return mScale;
        }
    }
}
