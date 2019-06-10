package com.example.zhdaily.bean;

import java.io.Serializable;
import java.util.List;

public class NewsBean implements Serializable {

    /**
     * date : 20190601
     * stories : [{"images":["https://pic2.zhimg.com/v2-d01ad4bd599ef21e0439040257123d45.jpg"],"type":0,"id":9711866,"ga_prefix":"060109","title":"5G 时代，地铁内信号差的情况会有改善吗?"},{"images":["https://pic1.zhimg.com/v2-2169716c5a398abc878d852951d6519c.jpg"],"type":0,"id":9711962,"ga_prefix":"060107","title":"为什么用剑插入人身体后，人在未死亡前无力还击了？"},{"images":["https://pic3.zhimg.com/v2-a4abfcecfd12fc8beaeccca74929265a.jpg"],"type":0,"id":9711860,"ga_prefix":"060106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-c041e9c1e28edc3100309532742509f2.jpg","type":0,"id":9712013,"ga_prefix":"053108","title":"百度最难捱的一夜：五名高管闪电辞职内幕"},{"image":"https://pic1.zhimg.com/v2-548d3d615b68aa27421475875d2b410c.jpg","type":0,"id":9711876,"ga_prefix":"053008","title":"高铁这么好的东西，美国人为什么不大力发展？"},{"image":"https://pic2.zhimg.com/v2-6fff58037d60ab3b6fb76d2574dff1d9.jpg","type":0,"id":9711821,"ga_prefix":"053120","title":"为什么西方人起名字都从已有的名字里选，而不是像中文一样自己造？"},{"image":"https://pic2.zhimg.com/v2-c5f519e3d68f064c356e094e6072b159.jpg","type":0,"id":9711871,"ga_prefix":"052908","title":"69 名血透患者在医院里感染丙肝病毒，不是第一次，可能也不是最后一次"},{"image":"https://pic3.zhimg.com/v2-36d637050b252a8785d1a728f1e99532.jpg","type":0,"id":9711813,"ga_prefix":"052807","title":"四川卧龙拍到的全球首例白色大熊猫，是怎么形成的？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-d01ad4bd599ef21e0439040257123d45.jpg"]
         * type : 0
         * id : 9711866
         * ga_prefix : 060109
         * title : 5G 时代，地铁内信号差的情况会有改善吗?
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;
        private String newsDate;

        public String getNewsDate() {
            return newsDate;
        }

        public void setNewsDate(String newsDate) {
            this.newsDate = newsDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    ", newsDate='" + newsDate + '\'' +
                    '}';
        }
    }

    public static class TopStoriesBean implements Serializable{
        /**
         * image : https://pic3.zhimg.com/v2-c041e9c1e28edc3100309532742509f2.jpg
         * type : 0
         * id : 9712013
         * ga_prefix : 053108
         * title : 百度最难捱的一夜：五名高管闪电辞职内幕
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
