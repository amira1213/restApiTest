package com.example.lenovog50.keygeneratorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GenerateKeyActivity extends AppCompatActivity {

    EditText keyname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_key);
        keyname=(EditText) findViewById(R.id.keyName);
    }

    protected void generateKey(View view)
    {   String keyName="";
        if(keyname.getText().toString().length()==0) keyName="unnamed";
        else keyName=keyname.getText().toString();
        MainActivity.keyList.add(keyName);
        Toast.makeText(getApplicationContext(),keyName+" key generated!",Toast.LENGTH_LONG).show();
        keyname.setText("");

        if(MainActivity.adapter!=null)
        MainActivity.adapter.notifyDataSetChanged();
    }


}
