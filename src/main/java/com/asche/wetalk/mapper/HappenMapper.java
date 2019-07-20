package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.Happen;
import com.asche.wetalk.entity.HappenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HappenMapper {
    int countByExample(HappenExample example);

    int deleteByExample(HappenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Happen record);

    int insertSelective(Happen record);

    List<Happen> selectByExample(HappenExample example);

    Happen selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Happen record, @Param("example") HappenExample example);

    int updateByExample(@Param("record") Happen record, @Param("example") HappenExample example);

    int updateByPrimaryKeySelective(Happen record);

    int updateByPrimaryKey(Happen record);

    int like(Integer id);

    int unLike(Integer id);
}