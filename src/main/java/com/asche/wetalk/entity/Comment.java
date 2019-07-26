package com.asche.wetalk.entity;

//import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

//@Document(indexName = "wetalk", type = "comment", replicas = 0)
public class Comment implements Serializable {
    private Integer id;

    private Integer parentid;

    private Integer bodyid;

    private Integer authorid;

    private Integer type;

    private String content;

    private String time;

    private Integer likenum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getBodyid() {
        return bodyid;
    }

    public void setBodyid(Integer bodyid) {
        this.bodyid = bodyid;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentid=").append(parentid);
        sb.append(", bodyid=").append(bodyid);
        sb.append(", authorid=").append(authorid);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", time=").append(time);
        sb.append(", likenum=").append(likenum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}