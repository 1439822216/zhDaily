package com.example.zhdaily.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zhdaily.R;
import com.example.zhdaily.adapter.CommentsAdapter;
import com.example.zhdaily.bean.BeforeBean;
import com.example.zhdaily.bean.CommentsBean;
import com.example.zhdaily.bean.LongBean;
import com.example.zhdaily.bean.SQLBean;
import com.example.zhdaily.bean.ShortBean;
import com.example.zhdaily.fragment.CollectionFragment;
import com.example.zhdaily.fragment.HomeFragment;
import com.example.zhdaily.utils.DBUtils;
import com.example.zhdaily.utils.OkHttpUtil;
import com.example.zhdaily.utils.WebAdd;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class WebActivity extends AppCompatActivity {
    RecyclerView rv_com;
    WebView web_content;
    BeforeBean.StoriesBean storiesBean;
    ExpandableListView expandableListView;
    OkHttpUtil instance = OkHttpUtil.getInstance();
    String id = new String();
    MyHandle myHandle;
    Gson gson;
    List<LongBean.CommentsBean> longComments;
    List<ShortBean.CommentsBean> shortComments;
    List<String> grouplist;
    List<CommentsBean> longList;//长评论集合
    List<CommentsBean> shortList;//短评论集合
    List<List<CommentsBean>> childList;
    List<CommentsBean> allList;//总集合
    CommentsAdapter commentsAdapter;
    TextView tv_commentTitlr,tv_collectionTitle;
    DBUtils dbUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();

        myHandle = new MyHandle();
        gson = new Gson();
        longComments = new ArrayList<>();
        shortComments = new ArrayList<>();
        longList = new ArrayList<>();
        shortList = new ArrayList<>();
        grouplist = new ArrayList<>();
        childList = new ArrayList<>();
        allList = new ArrayList<>();
        grouplist.add("长评论");
        grouplist.add("短评论");
        storiesBean = new BeforeBean.StoriesBean();
        Intent data = getIntent();
        storiesBean = (BeforeBean.StoriesBean)data.getSerializableExtra("bean");
        id = storiesBean.getId()+"";

        dbUtils =  DBUtils.getInstance(getApplicationContext());
        boolean b = dbUtils.queryOneByColl(new SQLBean(id, null, null, null));
        if(b){
            tv_collectionTitle.setText("已收藏");
            Drawable drawable = getResources().getDrawable(R.drawable.shoucangcheck);
            tv_collectionTitle.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        }

        web_content.getSettings().setJavaScriptEnabled(true);
        web_content.getSettings().setDomStorageEnabled(true);
        web_content.getSettings().setSupportMultipleWindows(true);
        web_content.getSettings().setLoadWithOverviewMode(true);
        web_content.getSettings().setUseWideViewPort(true);
        web_content.getSettings().setAppCacheEnabled(true);//是否使用缓存
        web_content.getSettings().setLoadsImagesAutomatically(true);//加载图片

        web_content.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web_content.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);
                view.loadUrl("javascript:function setTop(){document.querySelector('.header-for-mobile').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.bottom-recommend').style.display=\"none\";}setTop();");
            }

        });
        web_content.loadUrl(WebAdd.GETNEWS + id);

        //获取长评论
        initLongData();
        initShortData();
        rv_com.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commentsAdapter = new CommentsAdapter(getApplicationContext());
        commentsAdapter.setData(allList);
        //添加Android自带的分割线
        rv_com.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_com.setAdapter(commentsAdapter);
        tv_collectionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_collectionTitle.getText().toString().equals("收藏")){
                    boolean b1 = dbUtils.insertNewByColl(new SQLBean(String.valueOf(storiesBean.getId()), storiesBean.getTitle(), storiesBean.getImages().get(0), storiesBean.getNewsDate()));
                    if(b1){
                        tv_collectionTitle.setText("已收藏");
                        Drawable drawable = getResources().getDrawable(R.drawable.shoucangcheck);
                        tv_collectionTitle.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                        Toast.makeText(WebActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                    }
                }else if(tv_collectionTitle.getText().toString().equals("已收藏")){
                    boolean b1 = dbUtils.deleteNewByColl(new SQLBean(String.valueOf(storiesBean.getId()), storiesBean.getTitle(), storiesBean.getImages().get(0), storiesBean.getNewsDate()));
                    if(b1){
                        tv_collectionTitle.setText("收藏");
                        Drawable drawable = getResources().getDrawable(R.drawable.shoucang);
                        tv_collectionTitle.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                    }
                }
                

            }
        });
    }


    private void initShortData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Response response = instance.getDataSynFromNet(WebAdd.SHORT + id + WebAdd.SHORTSUFFIX);
                if (response != null){
                    Message message = new Message();
                    message.what = 2;
                    try {
                        message.obj = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myHandle.sendMessage(message);
                }

            }
        }.start();
    }

    private void initLongData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Response response = instance.getDataSynFromNet(WebAdd.LONG + id + WebAdd.LONGSUFFIX);
                if (response != null){
                    Message message = new Message();
                    message.what = 1;
                    try {
                        message.obj = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myHandle.sendMessage(message);
                }
            }
        }.start();
    }

    private class MyHandle extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    String longRes = (String) msg.obj;
                    LongBean longBean = gson.fromJson(longRes, LongBean.class);
                    longComments = longBean.getComments();
                    for (LongBean.CommentsBean l:longComments) {
                        CommentsBean commentsBean = new CommentsBean();
                        commentsBean.setId(l.getId());
                        commentsBean.setAuthor(l.getAuthor());
                        commentsBean.setAvatar(l.getAvatar());
                        commentsBean.setContent(l.getContent());
                        commentsBean.setTime(l.getTime());
                        LongBean.CommentsBean.ReplyToBean reply_to = l.getReply_to();
                        if (reply_to != null){
                            commentsBean.setReplyAuthor(reply_to.getAuthor());
                            commentsBean.setReplyContent( reply_to.getContent());
                        }
                        allList.add(commentsBean);
                    }
                    commentsAdapter.setData(allList);
                    tv_commentTitlr.setText("共有" + allList.size() + "条评论");
                    break;
                case 2:
                    String shortRes = (String) msg.obj;
                    ShortBean shortBean = gson.fromJson(shortRes, ShortBean.class);
                    shortComments = shortBean.getComments();
                    for (ShortBean.CommentsBean s:shortComments) {
                        CommentsBean commentsBean1 = new CommentsBean();
                        commentsBean1.setId(s.getId());
                        commentsBean1.setAuthor(s.getAuthor());
                        commentsBean1.setAvatar(s.getAvatar());
                        commentsBean1.setContent(s.getContent());
                        commentsBean1.setTime(s.getTime());
                        ShortBean.CommentsBean.ReplyToBean reply_to = s.getReply_to();
                        if (reply_to != null){
                            commentsBean1.setReplyAuthor(reply_to.getAuthor());
                            commentsBean1.setReplyContent(reply_to.getContent());
                        }
                        allList.add(commentsBean1);
                    }
                    commentsAdapter.setData(allList);
                    tv_commentTitlr.setText("共有" + allList.size() + "条评论");
                    break;
            }
        }
    }
    private void initView() {
        web_content = findViewById(R.id.web_content);
        //expandableListView = findViewById(R.id.expandableListView);
        rv_com = findViewById(R.id.rv_com);
        tv_commentTitlr = findViewById(R.id.tv_commentTitlr);
        tv_collectionTitle = findViewById(R.id.tv_collectionTitle);
    }
}
