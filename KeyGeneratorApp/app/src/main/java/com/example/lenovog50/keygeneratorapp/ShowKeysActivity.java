package com.example.lenovog50.keygeneratorapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.lenovog50.keygeneratorapp.R.id.text;

public class ShowKeysActivity extends AppCompatActivity {

    ListView listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_keys);

        listview=(ListView) findViewById(R.id.ListView);
        if(MainActivity.keyList!=null)
        Toast.makeText(getApplicationContext(),"size of keylist="+MainActivity.keyList.size(),Toast.LENGTH_LONG).show();

        listview.setAdapter(MainActivity.adapter);

        //***suppression d'une clé***
       /* listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView <?> parent, View view,int position,long id){
                SparseBooleanArray checkedpos=listview.getCheckedItemPositions();
                int size=listview.getCount();

                for(int keypos=size-1; keypos>=0;keypos--)
                {
                    if(checkedpos.get(keypos))
                    {
                        MainActivity.adapter.remove(MainActivity.keyList.get(keypos));

                    }
                }
                checkedpos.clear();
                MainActivity.adapter.notifyDataSetChanged();
              return false;
            }

        });*/
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView <?> parent, View view,int position,long id){
               final int checkedpos=position;




                new AlertDialog.Builder(ShowKeysActivity.this)
                .setIcon(R.drawable.delete1)
                .setTitle("Are you sure?")
                .setMessage("Delete the key")
                .setPositiveButton("yes",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.keyList.remove(checkedpos);
                        MainActivity.adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", null)
                .show();


                return true;
            }

        });
    }

    protected void sortKeys(View view)
    { 	   Collections.sort( MainActivity.keyList);
           MainActivity.adapter.notifyDataSetChanged();

        //Intent intent=new Intent(MainActivity.this,GenerateKeyActivity.class);
        //startActivity(intent);
    }
}
