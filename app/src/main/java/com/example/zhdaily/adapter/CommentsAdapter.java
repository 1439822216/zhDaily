package com.example.zhdaily.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.zhdaily.R;
import com.example.zhdaily.bean.CommentsBean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    private Context context;
    private List<CommentsBean> list;

    public CommentsAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<CommentsBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CommentsBean commentsBean = list.get(i);
        myViewHolder.tv_content.setText(commentsBean.getContent());
        myViewHolder.tv_name.setText(commentsBean.getAuthor());
        int time = commentsBean.getTime();
        Log.i("rrr",time + "");
        myViewHolder.tv_date.setText(timestampToString(time));
        Glide.with(context)
                .load(commentsBean.getAvatar())
                .into(myViewHolder.iv_head);
        if (commentsBean.getReplyAuthor() != null){
            myViewHolder.tv_reply_name.setText("//" + commentsBean.getReplyAuthor() + ":");
            myViewHolder.tv_reply_content.setText(commentsBean.getReplyContent());


        }
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
        TextView tv_group,tv_name,tv_content,tv_date,tv_reply_name,tv_reply_content;
        ImageView iv_head;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_reply_name = itemView.findViewById(R.id.tv_reply_name);
            tv_reply_content = itemView.findViewById(R.id.tv_reply_content);
        }
    }


    public static String timestampToString(Integer time){
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = (long)time*1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //方法一
            tsStr = dateFormat.format(ts);
            System.out.println(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

}
