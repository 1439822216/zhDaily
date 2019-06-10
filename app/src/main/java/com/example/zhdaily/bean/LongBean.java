package com.example.zhdaily.bean;

import java.util.List;

public class LongBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "LongBean{" +
                "comments=" + comments +
                '}';
    }

    public static class CommentsBean {
        /**
         * author : 小马
         * content : 虽然你想表达仅代表你个人观点的言论但是喷子要喷你还是轻而易举：你觉得xx就xx？
         大部分人表达的观点都是比较客观的，小部分人的观点就喜欢直接下定论，把“我觉得，我认为”删掉，直接给评价。
         我觉得知乎上一些写答案的人比写作文还会编。
         知乎作者很会编。
         * avatar : http://pic3.zhimg.com/0177d40d043ddee540c37d1a7d7269ee_im.jpg
         * time : 1559629532
         * id : 33220015
         * likes : 1
         * reply_to : {"content":"我认为加这个词是讲话严谨的表现。因为紧接着表达的肯定是看法而不是事实。曾经被某些拉投资的组织的人说：\u201c你讲话总是你觉得怎么样，你的世界都是想象中的吗？\u201d\n我反而想，你们明明说的是自己的看法，说着自己想象的如果对方付钱给你会过怎么样的生活，却伪装成讲事实的样子。还自以为是的批判别人幼稚，不跟你一样到处拉人投资赚钱就是自私对父母不负责？？？其实那些根本不确定，都是你的想象。\n我们理科生讲话可不愿意在讲观点的时候伪装成大众认定的事实。我的想法就是我的想法。","status":0,"id":33219110,"author":"2167241725"}
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private int id;
        private int likes;
        private ReplyToBean reply_to;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public static class ReplyToBean {
            /**
             * content : 我认为加这个词是讲话严谨的表现。因为紧接着表达的肯定是看法而不是事实。曾经被某些拉投资的组织的人说：“你讲话总是你觉得怎么样，你的世界都是想象中的吗？”
             我反而想，你们明明说的是自己的看法，说着自己想象的如果对方付钱给你会过怎么样的生活，却伪装成讲事实的样子。还自以为是的批判别人幼稚，不跟你一样到处拉人投资赚钱就是自私对父母不负责？？？其实那些根本不确定，都是你的想象。
             我们理科生讲话可不愿意在讲观点的时候伪装成大众认定的事实。我的想法就是我的想法。
             * status : 0
             * id : 33219110
             * author : 2167241725
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            @Override
            public String toString() {
                return "ReplyToBean{" +
                        "content='" + content + '\'' +
                        ", status=" + status +
                        ", id=" + id +
                        ", author='" + author + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CommentsBean{" +
                    "author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", time=" + time +
                    ", id=" + id +
                    ", likes=" + likes +
                    ", reply_to=" + reply_to +
                    '}';
        }
    }

}
