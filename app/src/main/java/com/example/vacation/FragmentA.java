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

public class FragmentA extends Fragment {
    private static String Aname;
    private TextView atv_id,atv_name,atv_money,atv_date,atv_inout;
    private ListView lv_mes1;
    private SQLiteDatabase db;
    private DBUser helper;
    private ArrayList<MesInfo> listData;
    private MesAdapter adapter;
    public FragmentA(){
    }
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragment,container,false);
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Bundle bundle1=this.getArguments();
        if(bundle1!=null){
            Aname=bundle1.getString("Auser");
        }
        atv_name=(TextView)getActivity().findViewById(R.id.tv_name);
        atv_money=(TextView)getActivity().findViewById(R.id.tv_money);
        atv_date=(TextView)getActivity().findViewById(R.id.tv_date);
        atv_inout=(TextView)getActivity().findViewById(R.id.tv_inout);
        lv_mes1=(ListView)getActivity().findViewById(R.id.lv_mes1);
        listData=new ArrayList<MesInfo>();
        helper=new DBUser(getActivity(),"user_db",null,1);
        db=helper.getWritableDatabase();

        String sql="select * from message where musername=? and inout='收入'";
        Cursor cursor=db.rawQuery(sql,new String[]{Aname});
        while(cursor.moveToNext()){
            MesInfo mes=new MesInfo();
            mes.setName(cursor.getString(cursor.getColumnIndex("name")));
            mes.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            mes.setDate(cursor.getString(cursor.getColumnIndex("date")));
            mes.setInout(cursor.getString(cursor.getColumnIndex("inout")));
            listData.add(mes);
        }
        adapter=new MesAdapter(this.getActivity(),listData);
        lv_mes1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
