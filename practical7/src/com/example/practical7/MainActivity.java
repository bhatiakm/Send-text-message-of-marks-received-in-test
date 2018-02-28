package com.example.practical7;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button save=(Button) findViewById(R.id.button1);
        Button marks=(Button) findViewById(R.id.button2);
        Button sms=(Button) findViewById(R.id.button3);
       save.setOnClickListener(this);
        marks.setOnClickListener(this);
        sms.setOnClickListener(this);
    }


	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		
		case R.id.button1:
		Intent i;
		i=new Intent(getBaseContext(),Save.class);
		startActivity(i);
		break;

		case R.id.button2:
		Intent i2;
		i2=new Intent(getBaseContext(),Marks.class);
		startActivity(i2);
		break;
		case R.id.button3:
			Intent i3;
			i3=new Intent(getBaseContext(),sms.class);
			startActivity(i3);
			break;
		
		}
	}
    
}
