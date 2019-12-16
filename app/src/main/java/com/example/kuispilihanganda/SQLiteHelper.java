package com.example.kuispilihanganda;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Soal.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "DashboardSoal";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_KATEGORI = "kategori";
    public static final String COLUMN_SOAL = "soal";
    public static final String COLUMN_PILIHAN1 = "pilihan1";
    public static final String COLUMN_PILIHAN2 = "pilihan2";
    public static final String COLUMN_PILIHAN3 = "pilihan3";
    public static final String COLUMN_PILIHAN4 = "pilihan4";
    public static final String COLUMN_JAWABAN = "jawaban";
    Context context;
    DashboardSoal dashboardSoal;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KATEGORI + " TEXT NOT NULL, " +
                COLUMN_SOAL + " TEXT NOT NULL, " +
                COLUMN_PILIHAN1 + " TEXT NOT NULL, " +
                COLUMN_PILIHAN2 + " TEXT NOT NULL, " +
                COLUMN_PILIHAN3 + " TEXT NOT NULL, " +
                COLUMN_PILIHAN4 + " TEXT NOT NULL, " +
                COLUMN_JAWABAN + " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean saveNewSoal(DashboardSoal dashboardSoal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KATEGORI, dashboardSoal.getKategori());
        values.put(COLUMN_SOAL, dashboardSoal.getSoal());
        values.put(COLUMN_PILIHAN1, dashboardSoal.getPilihan1());
        values.put(COLUMN_PILIHAN2, dashboardSoal.getPilihan2());
        values.put(COLUMN_PILIHAN3, dashboardSoal.getPilihan3());
        values.put(COLUMN_PILIHAN4, dashboardSoal.getPilihan4());
        values.put(COLUMN_JAWABAN, dashboardSoal.getJawaban());
        long result = db.insert(TABLE_NAME,null, values);
        db.close();
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public List<DashboardSoal> dashboardSoalList() {

        //regular query
        String query = "SELECT  * FROM " + TABLE_NAME;

        List<DashboardSoal> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                dashboardSoal = new DashboardSoal();

                dashboardSoal.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                dashboardSoal.setKategori(cursor.getString(cursor.getColumnIndex(COLUMN_KATEGORI)));
                dashboardSoal.setSoal(cursor.getString(cursor.getColumnIndex(COLUMN_SOAL)));
                dashboardSoal.setPilihan1(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN1)));
                dashboardSoal.setPilihan2(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN2)));
                dashboardSoal.setPilihan3(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN3)));
                dashboardSoal.setPilihan4(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN4)));
                dashboardSoal.setJawaban(cursor.getString(cursor.getColumnIndex(COLUMN_JAWABAN)));
                personLinkedList.add(dashboardSoal);
            } while (cursor.moveToNext());
        }
        return personLinkedList;
    }

    public DashboardSoal getDashboardSoal (long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        DashboardSoal receivedSoal = new DashboardSoal();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            receivedSoal.setKategori(cursor.getString(cursor.getColumnIndex(COLUMN_KATEGORI)));
            receivedSoal.setSoal(cursor.getString(cursor.getColumnIndex(COLUMN_SOAL)));
            receivedSoal.setPilihan1(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN1)));
            receivedSoal.setPilihan2(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN2)));
            receivedSoal.setPilihan3(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN3)));
            receivedSoal.setPilihan4(cursor.getString(cursor.getColumnIndex(COLUMN_PILIHAN4)));
            receivedSoal.setJawaban(cursor.getString(cursor.getColumnIndex(COLUMN_JAWABAN)));
        }
        return receivedSoal;
    }

    public void updateSoal(long personId, Context context, DashboardSoal updated) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        String sql ="UPDATE "+TABLE_NAME+" SET kategori = ?,soal = ?,pilihan1 = ?,pilihan2 = ?,pilihan3 = ?,pilihan4 = ?,jawaban = ? WHERE _id = ?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1,updated.getKategori());
        statement.bindString(2,updated.getSoal());
        statement.bindString(3,updated.getPilihan1());
        statement.bindString(4,updated.getPilihan2());
        statement.bindString(5,updated.getPilihan3());
        statement.bindString(6,updated.getPilihan4());
        statement.bindString(7,updated.getJawaban());
        statement.bindLong(8,personId);

        statement.execute();
        db.close();


        Toast.makeText(context, "Edit Berhasil.", Toast.LENGTH_SHORT).show();

    }

    public void deleteSoal(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Berhasil Dihapus.", Toast.LENGTH_SHORT).show();

    }
}
