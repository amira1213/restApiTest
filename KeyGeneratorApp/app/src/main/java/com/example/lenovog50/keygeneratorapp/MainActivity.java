package com.example.lenovog50.keygeneratorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> keyList;
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyList=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,MainActivity.keyList);

    }


    protected void addKey(View view)
    {
        Intent intent=new Intent(MainActivity.this,GenerateKeyActivity.class);
        startActivity(intent);
    }

    protected void showKeys(View view)
    {
        Intent intent=new Intent(MainActivity.this,ShowKeysActivity.class);
        startActivity(intent);
    }





}
