package com.example.vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentB extends Fragment {
    //声明控件
    private static String Bname;
    private TextView btv_id,btv_name,btv_money,btv_date,btv_inout;
    private ListView lv_mes2;
    private SQLiteDatabase db;
    private DBUser helper;
    private ArrayList<MesInfo> listData;
    private MesAdapter adapter;
    public FragmentB(){

    }
    //连接对应fragment界面
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragment_b,container,false);
    }
    //编写事件
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //接收从Activity传过来的值
        Bundle bundle2=this.getArguments();
        if(bundle2!=null){
            Bname=bundle2.getString("Buser");
        }
        //运用ListView进行遍历数据库并输出
        btv_name=(TextView)getActivity().findViewById(R.id.tv_name);
        btv_money=(TextView)getActivity().findViewById(R.id.tv_money);
        btv_date=(TextView)getActivity().findViewById(R.id.tv_date);
        btv_inout=(TextView)getActivity().findViewById(R.id.tv_inout);
        lv_mes2=(ListView)getActivity().findViewById(R.id.lv_mes2);
        listData=new ArrayList<MesInfo>();
        helper=new DBUser(getActivity(),"user_db",null,1);
        db=helper.getWritableDatabase();

        String sql="select * from message where musername=? and inout='支出'";
        Cursor cursor=db.rawQuery(sql,new String[]{Bname});
        while(cursor.moveToNext()){
            MesInfo mes=new MesInfo();
            mes.setName(cursor.getString(cursor.getColumnIndex("name")));
            mes.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            mes.setDate(cursor.getString(cursor.getColumnIndex("date")));
            mes.setInout(cursor.getString(cursor.getColumnIndex("inout")));
            listData.add(mes);
        }
        adapter=new MesAdapter(this.getActivity(),listData);
        lv_mes2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}