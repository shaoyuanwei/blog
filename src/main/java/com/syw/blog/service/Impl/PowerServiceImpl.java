package com.syw.blog.service.Impl;

import com.syw.blog.dao.PowerMapper;
import com.syw.blog.entity.Power;
import com.syw.blog.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper mapper;

    @Override
    public Power queryPowerById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Power> queryPowerList() {
        return null;
    }

    @Override
    public int insertPower(Power power) {
        return 0;
    }

    @Override
    public int updatePower(Power power) {
        return 0;
    }
}
