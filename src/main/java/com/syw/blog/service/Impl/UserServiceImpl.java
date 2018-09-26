package com.syw.blog.service.Impl;

import com.syw.blog.dao.UserMapper;
import com.syw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    

}
