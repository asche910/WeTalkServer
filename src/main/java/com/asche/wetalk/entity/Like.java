package com.asche.wetalk.entity;

public class Like {
    private Integer id;
    private Integer bodyId;
    private Integer authorId;
    private Integer type;

    public Like(Integer bodyId, Integer authorId, Integer type) {
        this.bodyId = bodyId;
        this.authorId = authorId;
        this.type = type;
    }

    public Like(Integer id, Integer bodyId, Integer authorId, Integer type) {
        this.id = id;
        this.bodyId = bodyId;
        this.authorId = authorId;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
