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
import com.example.drawerlayoutdemo.R;
import com.example.drawerlayoutdemo.activity.WebActivity;
import com.example.drawerlayoutdemo.bean.BeforeBean;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context context;
    private List<BeforeBean.StoriesBean> list;



    public RecycleAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setData(List<BeforeBean.StoriesBean> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        BeforeBean.StoriesBean storiesBean = list.get(i);
        myViewHolder.tv_title.setText(storiesBean.getTitle());
        Glide.with(context)
               .load(storiesBean.getImages().get(0))
               .into(myViewHolder.iv_image);
        myViewHolder.tv_date.setText(storiesBean.getNewsDate());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_date;
        private ImageView iv_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
