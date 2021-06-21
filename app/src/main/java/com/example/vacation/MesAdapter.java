package com.example.vacation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//适配器操作
public class MesAdapter extends BaseAdapter {
    private Context context;
    //创建列表
    private ArrayList<MesInfo> listData;

    public MesAdapter(Context context,ArrayList<MesInfo> listData){
        this.context=context;
        this.listData=listData;
    }
    //必要步骤
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            //这里将ListView的格式xml文件名称写入，名字千万别写错，否则调用后显示的格式会发生错误
            convertView=inflater.inflate(R.layout.list_item,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_money=(TextView)convertView.findViewById(R.id.tv_money);
            viewHolder.tv_date=(TextView)convertView.findViewById(R.id.tv_date);
            viewHolder.tv_inout=(TextView)convertView.findViewById(R.id.tv_inout);
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)convertView.getTag();
        MesInfo model=listData.get(position);
        viewHolder.tv_name.setText(model.getName());
        viewHolder.tv_money.setText(model.getMoney());
        viewHolder.tv_date.setText(model.getDate());
        viewHolder.tv_inout.setText(model.getInout());
        return convertView;
    }

}
class ViewHolder{
    public TextView tv_name;
    public TextView tv_money;
    public TextView tv_date;
    public TextView tv_inout;
}
