package com.example.my_cinema;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;

public class NewsActivity extends ActionBarActivity {


    DatabaseHelper 	sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
  
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		setTitle("Новинки");
		   
	       userList = (ListView)findViewById(R.id.userList);
	        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                Intent intent = new Intent(getApplicationContext(), MovieDetail.class);
	                intent.putExtra("id", id);
	                startActivity(intent);
	            }

		
	        });
	     
	        sqlHelper = new DatabaseHelper(getApplicationContext());
	        
	        sqlHelper.create_db();
	        
	       
	    }
	    
	@Override
   public void onResume() {
       super.onResume();
       try {
       	 db = sqlHelper.open();
            userCursor = db.rawQuery("SELECT  distinct M.names,D.date,M._id  FROM movies M,session D where M.names=D.namefilm", null);
            String[] headers = new String[]{"M.names", "D.date"};
            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
 
    	   /*
            db = sqlHelper.open();
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
            String[] headers = new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
     
*/
            
          
 
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
		getMenuInflater().inflate(R.menu.news, menu);
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
