package com.example.my_cinema;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
Button sklad,borecode,nameless,close,directors,news,cin;

TextView tvInfo;
SharedPreferences sp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        
        // получаем SharedPreferences, которое работает с файлом настроек
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        // полная очистка настроек
        // sp.edit().clear().commit();
        
        setTitle("Мої кінотеатри");
        sklad=(Button) findViewById(R.id.sklad);
        sklad.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(MainActivity.this,FilmActivity.class);             
				 startActivity(intent);
			}
        	
        });
        
        
        borecode=(Button) findViewById(R.id.borecode);
        borecode.setOnClickListener(new OnClickListener(){

     			@Override
     			public void onClick(View arg0) {
     				// TODO Auto-generated method stub
     				 Intent intent = new Intent(MainActivity.this,ActorsActivity.class);             
     				 startActivity(intent);
     			}
             	
             });
          
        nameless=(Button) findViewById(R.id.nameless);
        nameless.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				 Intent intent = new Intent(MainActivity.this,CategoryActivity.class);            
 				 startActivity(intent);
 			}
         	
         });
        
        directors=(Button) findViewById(R.id.directors);
        directors.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				 Intent intent = new Intent(MainActivity.this,DirectorsActivity.class);            
 				 startActivity(intent);
 			}
         	
         });
        news=(Button) findViewById(R.id.news);
        news.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				 Intent intent = new Intent(MainActivity.this,NewsActivity.class);            
 				 startActivity(intent);
 			}
         	
         });
        cin=(Button) findViewById(R.id.cin);
        cin.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				 Intent intent = new Intent(MainActivity.this,CinemasActivity.class);            
 				 startActivity(intent);
 			}
         	
         });
        
        close=(Button) findViewById(R.id.close);
        close.setOnClickListener(new OnClickListener(){

	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
        	
        });  
        
    }
    protected void onResume() {
        Boolean notif = sp.getBoolean("notif", false);
        String address = sp.getString("address", "");
        String text = "Notifications are "
            + ((notif) ? "enabled, address = " + address : "disabled");
        tvInfo.setText(text);
        super.onResume();
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       /* getMenuInflater().inflate(R.menu.main, menu);
        return true; */
    	 MenuItem mi = menu.add(0, 1, 0, "Адміністратору");
    	    mi.setIntent(new Intent(this, AdminActivity.class));
    	    return super.onCreateOptionsMenu(menu);
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
