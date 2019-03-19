package com.bestyiworld.entity;

/**
 * Created by Administrator on 2017/11/7.
 */
public class Article {
    private String id;
    private String userId;
    private String createTime;
    private String updateTime;
    private String title;
    private String type;
    private String author;
    private String text;
    private String category;
    private String status;
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Article(String id, String userId, String createTime, String updateTime, String title,
                   String type, String author, String text, String status,String category,String tag) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.type = type;
        this.author = author;
        this.text = text;
        this.status = status;
        this.category = category;
        this.tag = tag;
    }
}
