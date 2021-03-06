package com.syw.blog.api.controller;

import com.syw.blog.entity.User;
import com.syw.blog.ptool.JwtUtil;
import com.syw.blog.ptool.MD5Util;
import com.syw.blog.ptool.RandomUtil;
import com.syw.blog.ptool.ResponseMessage;
import com.syw.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 前端用户(博主)控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private static boolean falt = false;

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage doLogin(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        String username = (String) request.get("username");
        String password = MD5Util.EncoderByMD5((String) request.get("password"));

        if (username == null) {
            message = new ResponseMessage(0, "请输入账号", null);
            return message;
        }

        if (password == null) {
            message = new ResponseMessage(0, "请输入密码", null);
            return message;
        }

        User user = userService.queryUserByUsername(username);
        if (user == null) {
            message = new ResponseMessage(0, "不存在此账号", null);
            return message;
        }

        if (MD5Util.checkPassword(password, user.getPassword())) {
            String token = JwtUtil.generToken("lingling", null, null);
//                String md5Token = md5Util.EncoderByMD5(token);
            int i = userService.updateUserOnToken(token, user.getId());
            user.setToken(token);
            if (i == 1) {
                message = new ResponseMessage(0, "登录成功", user);
                logger.info("用户{}登录成功,token:{},加密后token:{}",user.getUsername(), token, token);
            } else {
                message = new ResponseMessage(0,"token没有更新成功", null);
                logger.error("用户{}登录失败，未更新token", user.getUsername());
            }
        } else {
            message = new ResponseMessage(-1, "登录失败，密码不正确", null);
        }

        return message;

    }

    /**
     * 账号检测是否可以注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage checkUsername(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        String username = (String) request.get("username");

        User user = userService.queryUserByUsername(username);
        if (user != null) {
            List<String> usernames = new ArrayList<>();
            int n = 0;
            while (n < 3) {
                String random = username + RandomUtil.generateRandom(4);
                if (userService.queryUserByUsername(random) == null) {
                    logger.info("用户不存在，并给用户推荐账号{}：{}", n + 1, random);
                    usernames.add(random);
                    n++;
                }
            }
            message = new ResponseMessage(101, "用户已经存在请重新输入或选择给出的用户名", usernames);
        } else {
            falt = true;
            message= new ResponseMessage(0, "该用户名可以使用", null);
        }

        return message;

    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage registeredUser(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        if (!falt) {
            message = new ResponseMessage(-1, "请先验证用户名是否可以使用", null);
            return message;
        }

        String username = (String) request.get("username");
        String password = MD5Util.EncoderByMD5((String) request.get("password"));
        String checkPassword = MD5Util.EncoderByMD5((String) request.get("checkPassword"));
        Integer age = (Integer) request.get("age");
        Integer gender = (Integer) request.get("gender");
        String profession = (String) request.get("profession");
        String telephone = (String) request.get("telephone");
        String email = (String) request.get("email");
        String introduction = (String) request.get("introduction");
        String name = (String) request.get("name");

        if (username == null) {
            message = new ResponseMessage(0, "请输入用户名", null);
            return message;
        }

        if (!MD5Util.checkPassword(password, checkPassword)) {
            message = new ResponseMessage(0, "两次密码不一致", null);
            return message;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);
            user.setGender(gender);
            user.setProfession(profession);
            user.setTelephone(telephone);
            user.setEmail(email);
            user.setIntroduction(introduction);
            user.setName(name);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setIsDel(0);
            int i = userService.insertUser(user);
            if (i == 1) {
                message = new ResponseMessage(0, "添加成功", user);
                logger.info("用户注册成功，用户信息{}", user);
            } else {
                message = new ResponseMessage(-1, "添加失败",null);
                logger.error("用户{}注册失败，用户信息{}", username, user);
            }
        }

        falt = false;

        return message;

    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage userInfo(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        Integer id = (Integer) request.get("id");

        User user = userService.queryUserById(id);

        logger.info("id存在:{}", id);

        if (user != null) {
            message = new ResponseMessage(0, "查询成功", user);
        } else {
            message = new ResponseMessage(-1, "查询失败", null);
        }

        return message;

    }

    @RequestMapping(value = "update", method =  RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage updateUser(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        Integer id = (Integer) request.get("id");
        String username = (String) request.get("username");
        Integer age = (Integer) request.get("age");
        Integer gender = (Integer) request.get("gender");
        String profession = (String) request.get("profession");
        String telephone = (String) request.get("telephone");
        String email = (String) request.get("email");
        String introduction = (String) request.get("introduction");
        String name = (String) request.get("name");

        

        return message;

    }

}
