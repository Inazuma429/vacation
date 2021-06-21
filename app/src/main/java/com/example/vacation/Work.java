package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Work extends AppCompatActivity implements View.OnClickListener {
    private Button addmes;
    private Button seemes;
    private Button delete;
    private TextView result;
    private TextView searchuser;
    private DBUser dbuser;
    private SQLiteDatabase sqLiteDatabase;
    private static String searchname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        addmes=(Button)findViewById(R.id.addmes);
        seemes=(Button)findViewById(R.id.seemes);
        delete=(Button)findViewById(R.id.delete);
        searchuser=(TextView)findViewById(R.id.searchuser);
        addmes.setOnClickListener(this);
        seemes.setOnClickListener(this);
        delete.setOnClickListener(this);

        dbuser=new DBUser(Work.this,"user_db",null,1);
        sqLiteDatabase=dbuser.getWritableDatabase();

        Intent intentdata=getIntent();
        String catchuser=intentdata.getStringExtra("muser");
        if(catchuser!=null){
            searchname=catchuser;
            searchuser.setText(searchname);
        }else{
            searchuser.setText(searchname);
        }
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            //跳转到添加数据界面
            case R.id.addmes:
                Intent intent=new Intent(Work.this,AddMes.class);
                intent.putExtra("auser",searchname);
                startActivity(intent);
                break;
            case R.id.seemes:
                //跳转到输出界面
                Intent intent1=new Intent(Work.this,Search.class);
                intent1.putExtra("buser",searchname);
                startActivity(intent1);
                break;
            case R.id.delete:
                //删除按钮
                sqLiteDatabase.delete("message","musername=?",new String[]{searchname});
                Toast.makeText(Work.this,"记入信息删除成功",Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }

}
