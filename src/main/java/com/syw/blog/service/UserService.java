package com.syw.blog.service;

import com.syw.blog.entity.User;

public interface UserService {

    User queryUserByUsername(String username);

    int updateUserOnToken(String token, Integer id);

    int insertUser(User user);

    User getUserInfo(String md5token, Integer idDel);

    User queryUserById(Integer id);

}
