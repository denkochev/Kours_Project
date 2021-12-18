package com.example.my_cinema;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BrondetailActivity extends ActionBarActivity {

	    TextView date;
	    TextView fio;
	    TextView kino;
	    TextView misce;
	    TextView mobile;
	    TextView teatr;
	    TextView timec;
        Button saveButton;
  
   
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
	
    String Cinema ="";
    String Fio ="";
    String Kino ="";
    String Misce ="";
    String Mobile ="";
    String Teatr ="";
    String Timec ="";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brondetail);
		saveButton =(Button) findViewById(R.id.saveButton);
	
        
		date =(TextView) findViewById(R.id.date);
		date.setText("");
		fio =(TextView) findViewById(R.id.fio);
		fio.setText("");
		kino =(TextView) findViewById(R.id.kino);
		kino.setText("");
		misce =(TextView) findViewById(R.id.misce);
		misce.setText("");
		teatr =(TextView) findViewById(R.id.teatr);
		teatr.setText("");
		mobile =(TextView) findViewById(R.id.mobile);
		mobile.setText("");
		timec =(TextView) findViewById(R.id.timec);
		timec.setText("");
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
    
        if (userId > 0) {
            
            userCursor = db.rawQuery("select * from bron where " +
                    DatabaseHelper.BRON_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            fio.setText("ФІО користувача - "+userCursor.getString(2));
            date.setText("Дата сеансу - "+userCursor.getString(1));
            kino.setText("Назва фільму бронювання - "+userCursor.getString(3));
            misce.setText("Місце бронювання- "+userCursor.getString(4));
            teatr.setText("Театр кінотеатру - "+userCursor.getString(6));
            mobile.setText("Мобільний телефон користувача - "+userCursor.getString(5));
            timec.setText("Час сеансу- "+userCursor.getString(7));
            saveButton.setText("Подзвонити клієнту- "+userCursor.getString(5));
            userCursor.close();
           
            
         
         

        } 
	      
  }
	
	
	
	
	
	 
	   private void goHome(){
	        
	        db.close();
	       
	        Intent intent = new Intent(this, FinishActivity.class);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        startActivity(intent);
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.brondetail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
