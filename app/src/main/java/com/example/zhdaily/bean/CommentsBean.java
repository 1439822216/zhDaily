package com.example.zhdaily.bean;

public class CommentsBean {
    private String author;
    private String content;
    private String avatar;
    private int time;
    private int id;
    private int likes;
    private String replyAuthor;
    private String replyContent;

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(String replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

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

    @Override
    public String toString() {
        return "CommentsBean{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", time=" + time +
                ", id=" + id +
                ", likes=" + likes +
                ", replyAuthor='" + replyAuthor + '\'' +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }
}
