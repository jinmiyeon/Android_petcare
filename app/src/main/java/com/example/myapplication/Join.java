package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Join extends Activity {

    EditText jId, jPw;
    Button btnRegistration, jbtnMain;

    SQLiteDatabase sqlDB;
    myDBHelper myHeler;
    //EditText가 아닌 다른 곳 클릭 시 키보드 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if(focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if(!rect.contains(x,y)){
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(),0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        jId=(EditText)findViewById(R.id.jId);
        jPw=(EditText)findViewById(R.id.jPw);
        btnRegistration=(Button)findViewById(R.id.j_Registration);
        jbtnMain=(Button)findViewById(R.id.j_main);
        myHeler=new myDBHelper(this);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB=myHeler.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO Join_info VALUES('"+
                        jId.getText().toString()+"','"+
                        jPw.getText().toString()+"')");
                sqlDB.close();

                Toast.makeText(getApplicationContext(),"회원가입 되었습니다.",Toast.LENGTH_LONG).show();

            }
        });
        //jbtnMain버튼을 누르면 메인으로 이동하기
        jbtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화면이동
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);//class import
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"로그인",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context) {//class import
            super(context, "LoginDB", null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Join_info(uId TEXT, uPassword TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS JOIN_INFO");
            onCreate(db);
        }
    }
}


