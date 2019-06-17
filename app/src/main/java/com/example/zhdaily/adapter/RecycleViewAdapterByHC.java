package com.example.zhdaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhdaily.R;
import com.example.zhdaily.activity.WebActivity;
import com.example.zhdaily.bean.BeforeBean;
import com.example.zhdaily.bean.NewsBean;
import com.example.zhdaily.bean.SQLBean;

import java.util.List;

public class RecycleViewAdapterByHC extends RecyclerView.Adapter<RecycleViewAdapterByHC.MyViewHoider> {

    private Context context;
    private List<SQLBean> list;

    public RecycleViewAdapterByHC(Context context,List<SQLBean> list){
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHoider onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,viewGroup,false);
        RecycleViewAdapterByHC.MyViewHoider myViewHolder = new RecycleViewAdapterByHC.MyViewHoider(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoider myViewHoider, int i) {
        SQLBean sqlBean = list.get(i);
        myViewHoider.tv_title.setText(sqlBean.getTitle());
        Glide.with(context)
                .load(sqlBean.getImage())
                .into(myViewHoider.iv_image);
        myViewHoider.tv_date.setText(sqlBean.getTimedata());
        myViewHoider.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeforeBean.StoriesBean storiesBean = new  BeforeBean.StoriesBean();
                storiesBean.setId(Integer.parseInt(sqlBean.getNewId()));
                storiesBean.setTitle(sqlBean.getTitle());
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("bean",storiesBean);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoider extends RecyclerView.ViewHolder{

        private TextView tv_title,tv_date;
        private ImageView iv_image;
        public MyViewHoider(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
