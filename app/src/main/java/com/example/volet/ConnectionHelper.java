package com.example.volet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class ConnectionHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME="database.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="myData";
    private static final String COLUMN_DES="description";
    private static final String COLUMN_AMOUNT="Amount";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_CATID="catId";


    ConnectionHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+" ("+ COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,  "+
                COLUMN_DES+ " TEXT,"+COLUMN_AMOUNT+ " FLOAT,"+COLUMN_DATE+ " TEXT,"+COLUMN_CATID+ " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addData(String description, double amount, String date,String catId){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DES,description);
        cv.put(COLUMN_AMOUNT,amount);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_CATID,catId);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllData() {
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =null;
        if(db !=null) {
        cursor = db.rawQuery(query, null);

        }
        return cursor;
    }
    void updateData(String row_id, String description, double amount, String date,String catId){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_DES,description);
        cv.put(COLUMN_AMOUNT,amount);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_CATID,catId);

        long result = db.update(TABLE_NAME,cv,"id=?",new String[]{row_id});
        if (result==-1){
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show();
    }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"id=?",new String[]{row_id} );
        if (result==-1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else
        {
          Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }


}
