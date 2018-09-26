package com.syw.blog.api.controller;

import com.syw.blog.ptool.ResponseMessage;
import com.syw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage registeredUser(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        String username = (String) request.get("username");
        String password = (String) request.get("password");

        return message;

    }





}
