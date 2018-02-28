package com.example.practical7;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Marks extends Activity implements OnClickListener {
	SQLiteDatabase db;
	int enrollno;
	String semester;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks);
        Button save=(Button) findViewById(R.id.button1);
        save.setOnClickListener(this);
       db=openOrCreateDatabase("mydb", MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS marks_info (id INTEGER PRIMARY KEY AUTOINCREMENT,enroll INTEGER ,sem TEXT,subject TEXT,t1 INTEGER,t2 INTEGER)");
        Spinner sp=(Spinner) findViewById(R.id.spinner1);
        Cursor c=db.rawQuery("SELECT enroll from student_info",null);
       final ArrayList<Integer> enroll=new ArrayList<Integer>();
        while(c.moveToNext()){
        	enroll.add(c.getInt(0));
        	
        }
        
        ArrayAdapter<Integer> ad1=new ArrayAdapter<Integer>(Marks.this,android.R.layout.simple_list_item_1,enroll);
        sp.setAdapter(ad1);
        final String sem[]={"1","3","5","7"};
        Spinner sp2=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> ad2=new ArrayAdapter<String>(Marks.this,android.R.layout.simple_list_item_1,sem);
        sp2.setAdapter(ad2);
       sp.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			enrollno=enroll.get(arg2);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	   
       });
       sp2.setOnItemSelectedListener(new OnItemSelectedListener(){

   		@Override
   		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
   				long arg3) {
   			semester=sem[arg2];
   		}

   		@Override
   		public void onNothingSelected(AdapterView<?> arg0) {
   			// TODO Auto-generated method stub
   			
   		}
       	   
          });
        
		
}

	@Override
	public void onClick(View arg0) {
		EditText e1=(EditText) findViewById(R.id.editText1);
		EditText e2=(EditText) findViewById(R.id.editText2);
		EditText e3=(EditText) findViewById(R.id.editText3);
		db.execSQL("INSERT INTO marks_info(enroll,sem,subject,t1,t2) VALUES("+enrollno+",'"+semester+"','"+e1.getText().toString()+"','"+e2.getText().toString()+"','"+e3.getText().toString()+"')");
Cursor c=db.rawQuery("SELECT * FROM marks_info", null);
		
			Toast.makeText(getBaseContext(),
"Marks entered in database successfully", Toast.LENGTH_LONG).show();
		
}
}