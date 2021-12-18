package com.example.my_cinema;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class SeansActivity extends ActionBarActivity {
	EditText nameBox;
    EditText yearBox;
    EditText primm;
    Button saveButton;
    TextView fullo;
    TextView cinema;
    TextView datec;
    TextView times;
    TextView fil;
    TextView adr;
    ImageView img;
    
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
	
    String den ="";
    String chas ="";
    String mov ="";
    String kint ="";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seans);
		
		nameBox = (EditText) findViewById(R.id.name);
        yearBox = (EditText) findViewById(R.id.year);
        primm = (EditText) findViewById(R.id.primm);
        saveButton = (Button) findViewById(R.id.saveButton);
        img = (ImageView) findViewById(R.id.misce);
        
        
        fullo =(TextView) findViewById(R.id.fullo);
        fullo.setText("");
        cinema =(TextView) findViewById(R.id.cinema);
        cinema.setText("");
        datec =(TextView) findViewById(R.id.datec);
        datec.setText("");
        times =(TextView) findViewById(R.id.times);
        times.setText("");
        fil =(TextView) findViewById(R.id.fil);
        fil.setText("");
        adr =(TextView) findViewById(R.id.ADRES);
        adr.setText("");
 
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
    
        if (userId > 0) {
            
            userCursor = db.rawQuery("select * from session S, cinemas C where S.cinema=C.name AND S._id" +
                    "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            cinema.setText("Назва кінотеатру - "+userCursor.getString(1));
            adr.setText("Адреса кінотеатру - "+userCursor.getString(6));
            kint = userCursor.getString(1);
            fil.setText("Фільм - "+userCursor.getString(3));
            mov = userCursor.getString(3);
            datec.setText("Дата сеансу - "+userCursor.getString(2));
            den=userCursor.getString(2);
            times.setText("Початок сеансу - "+userCursor.getString(4));
            chas=userCursor.getString(4);
          
            
            userCursor.close();
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            
         
         

        } 
	      
  }
	
	  public void misce(View view){
		  int misce = 1 + (int) (Math.random() * 10);
		  int ryad = 1 + (int) (Math.random() * 5);
		  primm.setText(ryad+" -ряд "+misce+" -місце");
	  }
	
	
	   public void save(View view){
		   ContentValues cv = new ContentValues();
	        cv.put(DatabaseHelper.BRON_DATE, den);
	        cv.put(DatabaseHelper.BRON_FIO, nameBox.getText().toString());
	        cv.put(DatabaseHelper.BRON_FILM, mov);
	        cv.put(DatabaseHelper.BRON_MISCE, primm.getText().toString());
	        cv.put(DatabaseHelper.BRON_PHONE, yearBox.getText().toString());
	        cv.put(DatabaseHelper.BRON_TEATR, kint);
	        cv.put(DatabaseHelper.BRON_VREMYA, chas);
	        String inser = "(NULL,'"+den+"', "+"'"+nameBox.getText().toString()+"', "+"'"+mov+"', "+"'"+primm.getText().toString()+"', "+"'"+yearBox.getText().toString()+"', "+
	        "'"+kint+"', "+"'"+chas+"');";
	        
	   
	        db.execSQL("INSERT INTO bron VALUES "+inser);
	      //  System.out.println("INSERT INTO bron VALUES "+inser);
	        if (userId > 0) {
	            db.update(DatabaseHelper.BRON, cv, DatabaseHelper.BRON_ID + "=" + String.valueOf(userId), null);
	        } else {
	            db.insert(DatabaseHelper.BRON, null, cv);
	        }
	        goHome();
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
		getMenuInflater().inflate(R.menu.seans, menu);
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
