package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.Like;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {

    /**
     * 点赞
     * @param like
     * @return
     */
    int insert(@Param("like") Like like);

    /**
     * 查询是否已经点过赞了
     * @param like
     * @return
     */
    Like find(@Param("like") Like like);

    /**
     * 取消点赞
     * @param like
     * @return
     */
    int delete(@Param("like")Like like);
}
