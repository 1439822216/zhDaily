package com.example.zhdaily.utils;

public class WebAdd {
    public static String NEWS = "https://news-at.zhihu.com/api/4/news/latest";//最新消息
    public static String GETNEWS = "http://daily.zhihu.com/story/";//根据id获取消息内容 /后加id
    public static String BEFORE = "https://news-at.zhihu.com/api/4/news/before/";//获取以前新闻 /后加日期
    public static String LONG = "https://news-at.zhihu.com/api/4/story/";//长评论
    public static String LONGSUFFIX = "/long-comments";//长评论后缀
    public static String SHORT = "https://news-at.zhihu.com/api/4/story/";//短评论
    public static String SHORTSUFFIX = "/short-comments";//短评论后缀
    public static String WEBCONTENT = "https://news-at.zhihu.com/api/4/news/";//消息内容
}
