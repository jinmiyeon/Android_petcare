package com.example.myapplication;

import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Join {

    EditText edtId,edtPw;
    Button btnJoin,btnLogin;

    int IdFlag=0;
    int PwFlag=0;


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

    /////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId=(EditText)findViewById(R.id.edtId);
        edtPw=(EditText)findViewById(R.id.edtPw);
        btnJoin=(Button)findViewById(R.id.mbtnJoin);
        btnLogin=(Button)findViewById(R.id.mbtnLogin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),Join.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"회원가입",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor;
                sqlDB=myHeler.getReadableDatabase();//읽기 전용으로 DB열기
                cursor=sqlDB.rawQuery("SELECT * FROM Join_info;",null);
                String edt1=null;
                String edt2=null;
                String pass1=null;
                String pass2=null;
                IdFlag=0;
                PwFlag=0;

                while(cursor.moveToNext()){
                    edt2=cursor.getString(0);
                    pass2=cursor.getString(1);
                    edt1=edtId.getText().toString();
                    pass1=edtPw.getText().toString();

                    if (edt2.equals(edt1)){
                        IdFlag=1;

                        if (pass2.equals(pass1)){
                            PwFlag=1;

                            Toast.makeText(getApplicationContext(),"로그인 되었습니다.",
                                    Toast.LENGTH_LONG).show();

                            Intent intent0 = new Intent(getApplicationContext(),Menu.class);
                            startActivity(intent0);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "비밀번호를 잘못 입력하였습니다.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
                cursor.close();
                sqlDB.close();

                if (IdFlag==0 && PwFlag==0){

                    Toast.makeText(getApplicationContext(),"존재하지 않는 계정입니다.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}




