package com.example.zhdaily.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeforeBean {

    /**
     * date : 20190531
     * stories : [{"images":["https://pic3.zhimg.com/v2-c12e18ce020499bafd88fc79594eaeae.jpg"],"type":0,"id":9711953,"ga_prefix":"053122","title":"小事 · 一辈子没有被人骗过，没别的，就为争一口气"},{"images":["https://pic2.zhimg.com/v2-2bd7adb27d13c61cba7653c63e257349.jpg"],"type":0,"id":9711821,"ga_prefix":"053120","title":"为什么西方人起名字都从已有的名字里选，而不是像中文一样自己造？"},{"images":["https://pic3.zhimg.com/v2-80085639f9715b916319d2bc231f27b2.jpg"],"type":0,"id":9712015,"ga_prefix":"053116","title":"猛龙拿下 NBA 总决赛首分，现在轮到勇士头疼了"},{"title":"如何评价《隐形守护者》中的各个角色?","ga_prefix":"053109","images":["https://pic2.zhimg.com/v2-3f137c486aea38b1cdd8c091128c0475.jpg"],"multipic":true,"type":0,"id":9711957},{"images":["https://pic1.zhimg.com/v2-576f3af4bffc14c16c3ec4a19aa9ed5c.jpg"],"type":0,"id":9712013,"ga_prefix":"053108","title":"百度最难捱的一夜：五名高管闪电辞职内幕"},{"images":["https://pic2.zhimg.com/v2-b09fd9a82c8d2c0bfd33c2d6f0a71365.jpg"],"type":0,"id":9711816,"ga_prefix":"053107","title":"为什么科学家要从死鲸身上挖耳屎？"},{"images":["https://pic3.zhimg.com/v2-3f22ff3e599bb3af6b5470370763e5b2.jpg"],"type":0,"id":9711908,"ga_prefix":"053106","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

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

    public static class StoriesBean implements Serializable {
        /**
         * images : ["https://pic3.zhimg.com/v2-c12e18ce020499bafd88fc79594eaeae.jpg"]
         * type : 0
         * id : 9711953
         * ga_prefix : 053122
         * title : 小事 · 一辈子没有被人骗过，没别的，就为争一口气
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images = new ArrayList<>();
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

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
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
                    ", multipic=" + multipic +
                    ", images=" + images +
                    ", newsDate='" + newsDate + '\'' +
                    '}';
        }
    }

}
