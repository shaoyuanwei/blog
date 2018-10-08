package com.syw.blog.service.Impl;

import com.syw.blog.dao.UserMapper;
import com.syw.blog.entity.User;
import com.syw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User queryUserByUsername(String username) {
        return mapper.selectByUsername(username);
    }

    @Override
    public int updateUserOnToken(String token, Integer id) {
        User user = new User();
        user.setToken(token);
        user.setId(id);
        return mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int insertUser(User user) {
        return mapper.insert(user);
    }
}
