package com.example.my_cinema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
private static String DB_PATH; 
  

	private static String DB_NAME = "apteka";
    private static final int SCHEMA = 1; 

    
    static final String BRON = "bron";
    static final String BRON_ID = "_id";
    static final String BRON_DATE = "date";
    static final String BRON_FIO= "fio";
    static final String BRON_FILM = "kino";
    static final String BRON_MISCE = "misce";
    static final String BRON_PHONE = "mobile";
    static final String BRON_TEATR = "teatr";
    static final String BRON_VREMYA = "timec";

    static final String TABLE = "movies";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_CATEGORY = "category";
    static final String COLUMN_NAME = "names";
    static final String COLUMN_PRODUCER = "producer";
    static final String COLUMN_YEAR = "year";
    static final String COLUMN_ACTORS = "actor";
    
    static final String SEANS_FILM = "namefilm";
    static final String SEANS_DATE = "date";

    
    static final String KINOTEATRI = "cinemas";
    static final String CINEMA_NAME = "name";
    static final String CINEMA_CITY = "city";
    
    
    static final String COLUMN_DATE = "date";
    static final String COLUMN_TIME = "time";
    
    
    
     private Context myContext;
	
	

    public DatabaseHelper(Context context) {
    	 super(context, DB_NAME, null, SCHEMA);
         this.myContext=context;
         DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
      
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        
    }
    
    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
              
                myInput = myContext.getAssets().open(DB_NAME);
               
                String outFileName = DB_PATH;
 
               
                myOutput = new FileOutputStream(outFileName);
 
                
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
 
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    
    public SQLiteDatabase open()throws SQLException {
    	 
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}