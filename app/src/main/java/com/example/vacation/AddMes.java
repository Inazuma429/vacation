package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

//使用了RadioGroup
public class AddMes extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private TextView amuser;
    private EditText amname;
    private EditText amoney;
    private EditText amdate;
    private RadioGroup aminout;
    private RadioButton amin;
    private RadioButton amout;
    private Button amcreate;
    private DBUser dbuser;
    private SQLiteDatabase sqLiteDatabase;

    //loginname存储账号信息，将其提前填在账号框里，可修改
    private String user_str="";
    private String name_str="";
    private String money_str="";
    private String date_str="";
    private String inout_str="";
    private static String loginname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mes);

        amuser=(TextView)findViewById(R.id.amuser);
        amname=(EditText)findViewById(R.id.amname);
        amoney=(EditText)findViewById(R.id.amoney);
        amdate=(EditText)findViewById(R.id.amdate);
        aminout=(RadioGroup)findViewById(R.id.aminout);
        amin=(RadioButton)findViewById(R.id.amin);
        amout=(RadioButton)findViewById(R.id.amout);
        amcreate=(Button)findViewById(R.id.amcreate);

        aminout.setOnCheckedChangeListener(this);
        amcreate.setOnClickListener(this);
        amdate.setOnClickListener(this);

        dbuser=new DBUser(AddMes.this,"user_db",null,1);
        sqLiteDatabase=dbuser.getWritableDatabase();

        Intent intentdata=getIntent();
        String CatchUser=intentdata.getStringExtra("auser");
        if(CatchUser!=null){
            loginname=CatchUser;
            amuser.setText(loginname);
        }else{
            amuser.setText(loginname);
        }

    }

    //向数据表message添加数据
    @Override
    public void onClick(View view){
        switch (view.getId()){
            //点击日期的编辑框，弹出选择日期的界面
            case R.id.amdate:
                Calendar cal=Calendar.getInstance();
                //month要+1
                DatePickerDialog date=new DatePickerDialog(AddMes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //选择后显示
                        amdate.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                    }
                },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)+1);
                date.show();
                break;
            case R.id.amcreate:
                user_str=amuser.getText().toString();
                name_str=amname.getText().toString();
                money_str=amoney.getText().toString()+"元";
                date_str=amdate.getText().toString();

                ContentValues values=new ContentValues();
                values.put("musername",user_str);
                values.put("name",name_str);
                values.put("money",money_str);
                values.put("date",date_str);
                values.put("inout",inout_str);
                sqLiteDatabase.insert("message",null,values);

                finish();
                break;
                default:
                    break;
        }
    }

    //RadioButton选择并传值
    @Override
    public void onCheckedChanged(RadioGroup radioGroup,int checkedID){
        RadioButton rb=(RadioButton)findViewById(checkedID);
        inout_str=rb.getText().toString();
    }
}
