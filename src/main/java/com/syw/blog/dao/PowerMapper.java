package com.syw.blog.dao;

import com.syw.blog.entity.Power;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
}