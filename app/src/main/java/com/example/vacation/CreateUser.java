package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity implements View.OnClickListener {

    private Button ccreate;
    private EditText cuser;
    private EditText cpass;
    private SQLiteDatabase sqLiteDatabase;
    private DBUser dbuser;

    private String username_str="";
    private String password_str="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        ccreate=(Button)findViewById(R.id.ccreate);
        cuser=(EditText)findViewById(R.id.cuser);
        cpass=(EditText)findViewById(R.id.cpass);
        dbuser = new DBUser(CreateUser.this,"user_db",null,1);
        sqLiteDatabase=dbuser.getWritableDatabase();

        ccreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_str=cuser.getText().toString();
                password_str=cpass.getText().toString();

                //判定账号是否已注册
                Cursor cursor=sqLiteDatabase.query("user",new String[]{"username"},"username=?",new String[]{username_str},null,null,null);
                if(cursor.getCount()!=0){
                    Toast.makeText(CreateUser.this,"该用户已注册",Toast.LENGTH_SHORT).show();
                }else {
                    dbuser.addData(sqLiteDatabase, username_str, password_str);
                    Toast.makeText(CreateUser.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CreateUser.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
