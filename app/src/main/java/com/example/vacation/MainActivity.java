package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //声明控件
    private Button create;
    private Button login;
    private EditText user;
    private EditText pass;
    private DBUser dbuser;
    private SharedPreferences.Editor editor;
    private SQLiteDatabase sqLiteDatabase;
    private SharedPreferences sp;

    //调用方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login);
        create=(Button)findViewById(R.id.create);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        login.setOnClickListener(this);
        create.setOnClickListener(this);

        //创建数据库
        dbuser=new DBUser(MainActivity.this,"user_db",null,1);
        sqLiteDatabase=dbuser.getWritableDatabase();

        //该栏原定用于完成“记住密码”的功能，但该功能未实现
        sp=getSharedPreferences("user_mes",MODE_PRIVATE);
        editor=sp.edit();
        if(sp.getBoolean("flag",false)){
            String user_read=sp.getString("user","");
            String pass_read=sp.getString("pass","");
            user.setText(user_read);
            pass.setText(pass_read);
        }

    }

    //设置按钮点击事件
    @Override
    public void onClick(View view){
        //分“登录”和“注册”两个按钮
        switch (view.getId()){
            //登录
            case R.id.login:
                //取值
                String user_str=user.getText().toString();
                String pass_str=pass.getText().toString();
                //判定
                if(user_str.equals("")||pass_str.equals("")){
                    Toast.makeText(this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    //遍历user表，若账号不存在证明未注册，提示错误信息
                    Cursor cursor=sqLiteDatabase.query("user",new String[]{"password"},"username=?",new String[]{user_str},null,null,null);
                    if(cursor.moveToNext()){
                        String pass_query=cursor.getString(cursor.getColumnIndex("password"));
                        if(pass_str.equals(pass_query)){
                            //提示框
                            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Work.class);
                            intent.putExtra("muser",user_str);
                            startActivity(intent);
                        }else{
                            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                //注册，跳转页面
            case R.id.create:
                Intent intent=new Intent(MainActivity.this,CreateUser.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
