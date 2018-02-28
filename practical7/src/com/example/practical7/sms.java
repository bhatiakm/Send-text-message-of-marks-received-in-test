package com.example.practical7;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class sms  extends Activity implements OnClickListener {
SQLiteDatabase db;
int enrollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        Button save=(Button) findViewById(R.id.button1);
        save.setOnClickListener(this);
        db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        Spinner sp=(Spinner) findViewById(R.id.spinner1);
        Cursor c=db.rawQuery("SELECT enroll from student_info",null);
       final ArrayList<Integer> enroll=new ArrayList<Integer>();
        while(c.moveToNext()){
        	enroll.add(c.getInt(0));
        	
        }
        
        ArrayAdapter<Integer> ad1=new ArrayAdapter<Integer>(sms.this,android.R.layout.simple_list_item_1,enroll);
        sp.setAdapter(ad1);
        sp.setOnItemSelectedListener(new OnItemSelectedListener(){
    		@Override    		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
    				long arg3) {
    			enrollno=enroll.get(arg2);
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
		SmsManager m=SmsManager.getDefault();
		String no = null;
		int t1 = 0;
		int t2 = 0;
		Cursor c=db.rawQuery("SELECT * from student_info where enroll="+enrollno, null);
		while(c.moveToNext()){
			no=c.getString(2);
		}
		//Toast.makeText(getBaseContext(),"SELECT * from marks_info where enroll="+enrollno+" and subject='"+e1.getText().toString()+"'" , Toast.LENGTH_LONG).show();
		Cursor c2=db.rawQuery("SELECT * from marks_info where enroll="+enrollno+" and subject='"+e1.getText().toString()+"'", null);
		while(c2.moveToNext()){
		t1=c2.getInt(4);
		t2=c2.getInt(5);
		}
		
		Toast.makeText(getBaseContext(), "SMS Sent",Toast.LENGTH_LONG).show();
		m.sendTextMessage(no,null,"Test 1="+Integer.toString(t1)+"\n Test 2="+Integer.toString(t2),null,null);
		
	}
}
