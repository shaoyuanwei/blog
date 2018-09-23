package com.syw.blog.service;

import com.syw.blog.entity.Power;

import java.util.List;

public interface PowerService {

    Power queryPowerById(Integer id);

    List<Power>  queryPowerList();

    int insertPower(Power power);

    int updatePower(Power power);

}
