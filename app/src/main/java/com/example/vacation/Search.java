package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Search extends AppCompatActivity implements View.OnClickListener{

    //声明，注意RelativeLayout也需要声明，此处用来嵌入fragment
    private FragmentManager fragmentManager;
    private RelativeLayout content;
    private TextView item1,item2,item3;
    private static String thename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        content=(RelativeLayout)findViewById(R.id.content);
        item1=(TextView)findViewById(R.id.item1);
        item2=(TextView)findViewById(R.id.item2);
        item3=(TextView)findViewById(R.id.item3);

        fragmentManager=getSupportFragmentManager();

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);

        Intent intentdata=getIntent();
        String catchuser=intentdata.getStringExtra("buser");
        if(catchuser!=null){
            thename=catchuser;
        }
    }

    //点击不同按钮跳转至不同的fragment
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //Activity向Fragment传值，使用Bundle
            case R.id.item1:
                FragmentA fragmentA=new FragmentA();
                Bundle bundle1=new Bundle();
                bundle1.putString("Auser",thename);
                fragmentA.setArguments(bundle1);
                FragmentTransaction transaction1=fragmentManager.beginTransaction();
                transaction1.replace(R.id.content,fragmentA);
                transaction1.commit();
                break;
            case R.id.item2:
                FragmentB fragmentB=new FragmentB();
                Bundle bundle2=new Bundle();
                bundle2.putString("Buser",thename);
                fragmentB.setArguments(bundle2);
                FragmentTransaction transaction2=fragmentManager.beginTransaction();
                transaction2.replace(R.id.content,fragmentB);
                transaction2.commit();
                break;
            case R.id.item3:
                FragmentC fragmentC=new FragmentC();
                Bundle bundle3=new Bundle();
                bundle3.putString("Cuser",thename);
                fragmentC.setArguments(bundle3);
                FragmentTransaction transaction3=fragmentManager.beginTransaction();
                transaction3.replace(R.id.content,fragmentC);
                transaction3.commit();
                break;
                default:
                    break;
        }
    }
}
