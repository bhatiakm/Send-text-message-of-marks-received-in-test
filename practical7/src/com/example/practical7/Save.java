package com.example.practical7;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Save extends Activity implements OnClickListener {
	SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save=(Button) findViewById(R.id.button1);
        save.setOnClickListener(this);
     db=openOrCreateDatabase("mydb", MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student_info (enroll INTEGER PRIMARY KEY,name TEXT,phone TEXT)");
        

}

	public void onClick(View arg0) {
		try{
		EditText e1=(EditText) findViewById(R.id.editText1);
		EditText e2=(EditText) findViewById(R.id.editText2);
		EditText e3=(EditText) findViewById(R.id.editText3);
		db.execSQL("INSERT INTO student_info VALUES("+Integer.parseInt(e2.getText().toString())+",'"+e1.getText().toString()+"','"+e3.getText().toString()+"');");
		Log.d("my", "my");
		Cursor c=db.rawQuery("SELECT * FROM student_info", null);
		
			Toast.makeText(getBaseContext(), "Data Stored",Toast.LENGTH_LONG).show();
			
		
	}catch(Exception e){
		Toast.makeText(getBaseContext(),"Data already stored.",Toast.LENGTH_LONG).show();
		
	}
	}
}
