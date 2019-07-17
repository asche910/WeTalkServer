package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.Topicreply;
import com.asche.wetalk.entity.TopicreplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicreplyMapper {
    int countByExample(TopicreplyExample example);

    int deleteByExample(TopicreplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Topicreply record);

    int insertSelective(Topicreply record);

    List<Topicreply> selectByExampleWithBLOBs(TopicreplyExample example);

    List<Topicreply> selectByExample(TopicreplyExample example);

    Topicreply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Topicreply record, @Param("example") TopicreplyExample example);

    int updateByExampleWithBLOBs(@Param("record") Topicreply record, @Param("example") TopicreplyExample example);

    int updateByExample(@Param("record") Topicreply record, @Param("example") TopicreplyExample example);

    int updateByPrimaryKeySelective(Topicreply record);

    int updateByPrimaryKeyWithBLOBs(Topicreply record);

    int updateByPrimaryKey(Topicreply record);
}