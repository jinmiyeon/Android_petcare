package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class hospital_MainActivity extends AppCompatActivity {
    // Declare Variables
    ListView hospital_search_list;
    hospital_ListViewAdapter hospital_search_adapter;
    EditText hospital_search_edit;
    String[] hospital_search_list_title;
    ArrayList<hospital_search_ArrayList> hospital_search_title_arraylist = new ArrayList<hospital_search_ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_search);
        //동물병원 이름 리스트
        hospital_search_list_title = new String[]{
                "베스트 동물병원",
                "아이러브 동물병원",
                "레오 동물병원",
                "동물 의료센터",
                "WE 동물병원",
                "동물사랑 동물병원",
                "한마음 동물의료센터",
                "조은 동물병원"
        };

        hospital_search_list = (ListView) findViewById(R.id.hospital_search_list);

        for (int i = 0; i < hospital_search_list_title.length; i++) {
            hospital_search_ArrayList wp = new hospital_search_ArrayList(hospital_search_list_title[i]);
            // Binds all strings into an array
            hospital_search_title_arraylist.add(wp);
        }

        hospital_search_adapter = new hospital_ListViewAdapter(this, hospital_search_title_arraylist);


        hospital_search_list.setAdapter(hospital_search_adapter);

        // hospital_search_textfield
        hospital_search_edit = (EditText) findViewById(R.id.hospital_search_textfield);

        hospital_search_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = hospital_search_edit.getText().toString().toLowerCase(Locale.getDefault());
                hospital_search_adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}