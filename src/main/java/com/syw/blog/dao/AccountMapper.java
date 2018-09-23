package com.syw.blog.dao;

import com.syw.blog.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByUsername(@Param("username") String username);

    List<Account> queryAccountByLimit(@Param("param") Map<String, Object> param);

}