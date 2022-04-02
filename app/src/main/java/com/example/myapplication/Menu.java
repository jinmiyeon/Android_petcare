package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Menu extends AppCompatActivity {

    TextView ani1,ani2,ani3,ani4,ani5;
    EditText mal1,mal2,mal3,mal4,mal5;

    ViewFlipper v_fllipper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();

        Button HospitalSearch = findViewById(R.id.HospitalSearch);
        Button AnimalSearch = findViewById(R.id.AnimalSearch);
        Button Schedule = findViewById(R.id.Schedule);

        ImageButton imgbtn1 = findViewById(R.id.imageButton);

        HospitalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(Menu.this,hospital_MainActivity.class);
                startActivity(intent11);
            }
        });
        AnimalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(Menu.this,animal_MainActivity.class);
                startActivity(intent12);
            }
        });
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent13 = new Intent(Menu.this,Calendar.class);
                startActivity(intent13);
            }
        });

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(Menu.this,Stopwatch.class);
                startActivity(intent11);
            }
        });


        int[] images = {
                R.drawable.banner1,
                R.drawable.banner2,
                //R.drawable.banner3,
                R.drawable.banner4,
        };
        v_fllipper1 = findViewById(R.id.menu_imgslide);

        for (int image : images) {
            fllipperImages1(image);
        }


        ani1=(TextView)findViewById(R.id.anikind);
        ani2=(TextView)findViewById(R.id.aniname);
        ani3=(TextView)findViewById(R.id.anisex);
        ani4=(TextView)findViewById(R.id.aniage);
        ani5=(TextView)findViewById(R.id.animemo);

        ani1.setTextSize(24);
        ani2.setTextSize(24);
        ani3.setTextSize(24);
        ani4.setTextSize(24);
        ani5.setTextSize(24);

    }

    // 이미지 슬라이더 구현 메서드
    public void fllipperImages1(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_fllipper1.addView(imageView);      // 이미지 추가
        v_fllipper1.setFlipInterval(2000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper1.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper1.setInAnimation(this,android.R.anim.slide_in_left);
        v_fllipper1.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void bbb(View V){
        View dlgview = View.inflate(this,R.layout.registration_anidialog,null);

        mal1=(EditText)dlgview.findViewById(R.id.anikindedit);
        mal2=(EditText)dlgview.findViewById(R.id.aninameedit);
        mal3=(EditText)dlgview.findViewById(R.id.anisexedit);
        mal4=(EditText)dlgview.findViewById(R.id.aniageedit);
        mal5=(EditText)dlgview.findViewById(R.id.animemoedit);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);

        dlg.setView(dlgview);

        dlg.setTitle("반려 동물 등록");
        setTitleColor(Color.rgb(0,0,0));
        dlg.setIcon(R.drawable.propet1);

        dlg.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String kind = mal1.getText().toString();
                String name = mal2.getText().toString();
                String sex = mal3.getText().toString();
                String age = mal4.getText().toString();
                String memo = mal5.getText().toString();

                mal1.setTextColor(Color.rgb(0,0,0));

                ani2.setText("이름     " + name);
                ani1.setText("개체     " + kind);
                ani3.setText("성별     " + sex);
                ani4.setText("생일     " + age);
                ani5.setText("특이사항   \n" + memo);


            }
        });


        dlg.setPositiveButton("취소", null);

        //보이기
        dlg.show();
    }
}