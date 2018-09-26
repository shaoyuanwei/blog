package com.syw.blog.service;

import com.syw.blog.entity.Power;

import java.util.List;
import java.util.Map;

public interface PowerService {

    Power queryPowerById(Integer id);

    int insertPower(Power power);

    int updatePower(Power power);

    List<Power> queryPowerByLimit(Map<String, Object> param);

}
