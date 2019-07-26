package com.asche.wetalk.entity;

//import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

//@Document(indexName = "wetalk", type = "topic", replicas = 0)
public class Topic implements Serializable {
    private Integer id;

    private Integer authorid;

    private String title;

    private String content;

    private String coverurl;

    private String time;

    private Integer followernum;

    private Integer replynum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFollowernum() {
        return followernum;
    }

    public void setFollowernum(Integer followernum) {
        this.followernum = followernum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", authorid=").append(authorid);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", coverurl=").append(coverurl);
        sb.append(", time=").append(time);
        sb.append(", followernum=").append(followernum);
        sb.append(", replynum=").append(replynum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}