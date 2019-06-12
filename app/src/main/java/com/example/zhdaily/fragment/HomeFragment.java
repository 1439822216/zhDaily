package com.example.zhdaily.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zhdaily.R;
import com.example.zhdaily.activity.WebActivity;
import com.example.zhdaily.adapter.RecycleAdapter;
import com.example.zhdaily.bean.BeforeBean;
import com.example.zhdaily.bean.NewsBean;
import com.example.zhdaily.utils.OkHttpUtil;
import com.example.zhdaily.utils.WebAdd;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    Banner banner;
    NewsBean newsBean;
    String responseStr = "";
    Response response = null;
    Gson gson = new Gson();
    OkHttpUtil instance = OkHttpUtil.getInstance();
    List<NewsBean.TopStoriesBean> top_stories;
    MyHandle myHandle;
    List<String> topImages;
    List<String> topTitle;
    List<Integer> topId;
    List<NewsBean.StoriesBean> storiesBeans;
    RecyclerView rv_list;
    List<BeforeBean.StoriesBean> stories;
    RecycleAdapter recycleAdapter ;
    SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        recycleAdapter.setData(stories);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        top_stories = new ArrayList<>();
        myHandle = new MyHandle();
        topImages = new ArrayList<>();
        topTitle = new ArrayList<>();
        topId = new ArrayList<>();
        storiesBeans = new ArrayList<>();
        stories = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        initBefore();
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleAdapter = new RecycleAdapter(getActivity());
        recycleAdapter.setData(stories);
        rv_list.setAdapter(recycleAdapter);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topImages.clear();
                        topTitle.clear();
                        topId.clear();
                        stories.clear();
                        initData();
                        initBefore();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
        //判断是否滑动到底部
        if (nestedScrollView != null){
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView nestedScrollView,int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
                        BeforeBean.StoriesBean storiesBean = stories.get(stories.size() - 1);
                        String newsDate = storiesBean.getNewsDate();
                        initbeforeDay(newsDate);
                    }
                }
            });
        }
        return view;
    }
    private void initbeforeDay(String newsDate) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Response response = instance.getDataSynFromNet(WebAdd.BEFORE + newsDate);
                if (response!= null){
                    Message message = new Message();
                    message.what = 3;
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


    private void initBefore() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                // List<String> beforeDate = getBeforeDate(3);
                List<String> threeDate = getThreeDate();
                for (int i =0 ; i < threeDate.size();i++){
                    Log.i("www",threeDate.get(i));
                    Response response = instance.getDataSynFromNet(WebAdd.BEFORE + threeDate.get(i));
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
            }
        }.start();
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Response response = instance.getDataSynFromNet(WebAdd.NEWS);
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
    private void initView(View view) {
        banner = view.findViewById(R.id.banner);
        rv_list = view.findViewById(R.id.rv_list);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

    }
    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }
    }
    private class MyHandle extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 1:
                    responseStr = (String) msg.obj;
                    NewsBean newsBean = gson.fromJson(responseStr, NewsBean.class);
                    top_stories = newsBean.getTop_stories();
                    for (int i = 0 ; i< top_stories.size(); i++){
                        topImages.add(top_stories.get(i).getImage());
                        topTitle.add(top_stories.get(i).getTitle());
                        topId.add(top_stories.get(i).getId());
                    }
                    banner.setImages(topImages);
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                    banner.setBannerTitles(topTitle);
                    banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            BeforeBean.StoriesBean storiesBean = new BeforeBean.StoriesBean();
                            storiesBean.setId(topId.get(position));
                            storiesBean.setTitle(topTitle.get(position));
                            storiesBean.getImages().add(topImages.get(position));
                            Intent intent = new Intent(getActivity(), WebActivity.class);
                            intent.putExtra("bean",storiesBean);
                            startActivity(intent);
                        }
                    });
                    banner.start();

                    break;
                case 2:
                    BeforeBean beforeBean = gson.fromJson((String) msg.obj, BeforeBean.class);
                    String date = beforeBean.getDate();
                    List<BeforeBean.StoriesBean> storiesTemp = beforeBean.getStories();

                    for (BeforeBean.StoriesBean s: storiesTemp) {
                        s.setNewsDate(date);
                        stories.add(s);
                    }

                    recycleAdapter.setData(stories);

                    break;
                case 3:
                    BeforeBean beforeDayBean = gson.fromJson((String) msg.obj, BeforeBean.class);
                    String beforeDate = beforeDayBean.getDate();
                    List<BeforeBean.StoriesBean> beforeDateStories = beforeDayBean.getStories();

                    for (BeforeBean.StoriesBean storiesBean:beforeDateStories) {
                        storiesBean.setNewsDate(beforeDate);
                        stories.add(storiesBean);
                    }
                    recycleAdapter.setData(stories);

                    break;
            }
        }
    }
    public String formatDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        return date;
    }
    //获取三天的日期
    public List<String> getThreeDate(){
        List<String> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        String date = simpleDateFormat.format(calendar.getTime());
        list.add(date);
        calendar.add(Calendar.DATE,-1);
        String date1 = simpleDateFormat.format(calendar.getTime());
        list.add(date1);
        calendar.add(Calendar.DATE,-1);
        String date2 = simpleDateFormat.format(calendar.getTime());
        list.add(date2);
        return list;
    }

    public List<String> getBeforeDate(int num){
        List<String> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i = 1;i<=num;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-i);
            String date = simpleDateFormat.format(calendar.getTime());
            list.add(date);
        }
        return list;
    }
    //获取前一天日期
    public String formatByString(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date oldDate = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(oldDate);
            calendar.add(Calendar.DATE,-1);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
