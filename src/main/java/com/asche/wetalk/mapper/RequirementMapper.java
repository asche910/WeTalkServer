package com.asche.wetalk.mapper;

import com.asche.wetalk.entity.Requirement;
import com.asche.wetalk.entity.RequirementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementMapper {
    int countByExample(RequirementExample example);

    int deleteByExample(RequirementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Requirement record);

    int insertSelective(Requirement record);

    List<Requirement> selectByExampleWithBLOBs(RequirementExample example);

    List<Requirement> selectByExample(RequirementExample example);

    Requirement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Requirement record, @Param("example") RequirementExample example);

    int updateByExampleWithBLOBs(@Param("record") Requirement record, @Param("example") RequirementExample example);

    int updateByExample(@Param("record") Requirement record, @Param("example") RequirementExample example);

    int updateByPrimaryKeySelective(Requirement record);

    int updateByPrimaryKeyWithBLOBs(Requirement record);

    int updateByPrimaryKey(Requirement record);
}