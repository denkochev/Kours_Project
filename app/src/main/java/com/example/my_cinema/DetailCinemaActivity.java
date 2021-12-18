package com.example.my_cinema;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DetailCinemaActivity extends ActionBarActivity {
    SimpleCursorAdapter userAdapter;
    ListView userList;
    EditText userFilter;
	
	
    EditText nameBox;
    EditText yearBox;
  
    Button delButton;
    Button saveButton;
    TextView fullo;
    TextView filmname;
    TextView description;
 
   
    String bg ="";
  //  String core ="";
    
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_cinema);
		setTitle("Огляд кінотеатру");
		nameBox = (EditText) findViewById(R.id.name);
        yearBox = (EditText) findViewById(R.id.year);
       
   //     delButton = (Button) findViewById(R.id.deleteButton);
   //     saveButton = (Button) findViewById(R.id.saveButton);
        fullo =(TextView) findViewById(R.id.fullo);
        fullo.setText("");
        filmname =(TextView) findViewById(R.id.filmname);
        filmname.setText("");
        description =(TextView) findViewById(R.id.description);
        description.setText("");
 
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
    
        if (userId > 0) {
        	
        	
        	
            userCursor = db.rawQuery("select * from " + DatabaseHelper.KINOTEATRI + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();

            nameBox.setText(userCursor.getString(3));
            fullo.setText("Номер телефону - "+String.valueOf(userCursor.getString(4)));
            yearBox.setText("Місто - "+String.valueOf(userCursor.getString(2)));
            description.setText("Адреса - "+String.valueOf(userCursor.getString(1)));
            bg = userCursor.getString(3);
            
            userCursor.close();
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            
         
         
        } else {
            
            delButton.setVisibility(View.GONE);
        }
	      
        
        
	       userList = (ListView)findViewById(R.id.userList);
	        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                Intent intent = new Intent(getApplicationContext(), SeansActivity.class);
	                intent.putExtra("id", id);
	                startActivity(intent);
	            }

		
	        });
	      
	        sqlHelper = new DatabaseHelper(getApplicationContext());
	        sqlHelper.create_db();
  }
	

	

	   private void goHome(){
	        
	        db.close();
	       
	        Intent intent = new Intent(this, MovieDetail.class);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        startActivity(intent);
	    }
	
		@Override
	    public void onResume() {
	        super.onResume();
	        try {

	        	
	        //	System.out.println(bg);
	        	 db = sqlHelper.open();
	             userCursor = db.rawQuery("select * from session WHERE cinema='"+bg+"'", null);
	             String[] headers = new String[]{DatabaseHelper.COLUMN_DATE, DatabaseHelper.COLUMN_TIME};
	             userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
	                     userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
	           
	  
	             userList.setAdapter(userAdapter);
	         }
	         catch (SQLException ex){}
	     }
	    @Override
	    public void onDestroy(){
	        super.onDestroy();
	        
	        db.close();
	        userCursor.close();
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_cinema, menu);
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
