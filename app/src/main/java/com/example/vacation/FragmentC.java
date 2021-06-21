package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentC extends Fragment {
    //声明控件
    private static String Cname;
    private TextView ctv_id,ctv_name,ctv_money,ctv_date,ctv_inout;
    private ListView lv_mes3;
    private SQLiteDatabase db;
    private DBUser helper;
    private ArrayList<MesInfo> listData;
    private MesAdapter adapter;

    public FragmentC(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragment_c,container,false);
    }
    //事件
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Bundle bundle3=this.getArguments();
        if(bundle3!=null){
         Cname=bundle3.getString("Cuser");
        }
        ctv_name=(TextView)getActivity().findViewById(R.id.tv_name);
        ctv_money=(TextView)getActivity().findViewById(R.id.tv_money);
        ctv_date=(TextView)getActivity().findViewById(R.id.tv_date);
        ctv_inout=(TextView)getActivity().findViewById(R.id.tv_inout);
        lv_mes3=(ListView)getActivity().findViewById(R.id.lv_mes3);
        listData=new ArrayList<MesInfo>();
        helper=new DBUser(getActivity(),"user_db",null,1);
        db=helper.getWritableDatabase();

        String sql="select * from message where musername=?";
        Cursor cursor=db.rawQuery(sql,new String[]{Cname});
        while(cursor.moveToNext()){
            MesInfo mes=new MesInfo();
            mes.setName(cursor.getString(cursor.getColumnIndex("name")));
            mes.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            mes.setDate(cursor.getString(cursor.getColumnIndex("date")));
            mes.setInout(cursor.getString(cursor.getColumnIndex("inout")));
            listData.add(mes);
        }
        adapter=new MesAdapter(this.getActivity(),listData);
        lv_mes3.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}