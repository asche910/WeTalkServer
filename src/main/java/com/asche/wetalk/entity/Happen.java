package com.asche.wetalk.entity;

//import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

//@Document(indexName = "wetalk", type = "happen", replicas = 0)
public class Happen implements Serializable {
    private Integer id;

    private Integer authorid;

    private String content;

    private String time;

    private Integer likenum;

    private Integer commentnum;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", authorid=").append(authorid);
        sb.append(", content=").append(content);
        sb.append(", time=").append(time);
        sb.append(", likenum=").append(likenum);
        sb.append(", commentnum=").append(commentnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}