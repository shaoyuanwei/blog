package com.syw.blog.console.controller;

import com.syw.blog.entity.Account;
import com.syw.blog.entity.Power;
import com.syw.blog.ptool.ResponseMessage;
import com.syw.blog.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台权限控制器
 */
@RestController
@RequestMapping("/console/powwer")
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 权限列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage queryPowerList(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;

        Map<String, Object> param = new HashMap<>();
        param.put("isDel", 0);
        List<Power> list = powerService.queryPowerByLimit(param);

        message = new ResponseMessage(0, "查询成功", list);

        return message;

    }

    /**
     * 添加权限
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage insertPower(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        Integer powerCode = (Integer) request.get("powerCode");
        String powerName = (String) request.get("powerName");
        String url = (String) request.get("url");
        Integer fatherCode = (Integer) request.get("fatherCode");

        Power power = new Power();
        power.setPowerCode(powerCode);
        power.setPowerName(powerName);
        power.setCreateTime(new Date());
        power.setUpdateTime(new Date());
        power.setCreateAccount(account.getId());
        power.setUpdateAccount(account.getId());
        power.setIsDel(0);
        power.setUrl(url);
        power.setFatherCode(fatherCode);

        int i = powerService.insertPower(power);

        if (i == 1) {
            message = new ResponseMessage(0, "添加成功", null);
        } else {
            message = new ResponseMessage(-1, "添加失败", null);
        }

        return message;

    }

    /**
     * 修改权限
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage updatePower(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        Integer powerCode = (Integer) request.get("powerCode");
        String powerName = (String) request.get("powerName");
        String url = (String) request.get("url");
        Integer fatherCode = (Integer) request.get("fatherCode");
        Integer id = (Integer) request.get("id");

        Power power = powerService.queryPowerById(id);
        power.setPowerCode(powerCode);
        power.setPowerName(powerName);
        power.setUpdateTime(new Date());
        power.setUpdateAccount(account.getId());
        power.setUrl(url);
        power.setFatherCode(fatherCode);

        int i = powerService.updatePower(power);

        if (i == 1) {
            message = new ResponseMessage(0, "修改成功", null);
        } else {
            message = new ResponseMessage(-1, "修改失败", null);
        }

        return message;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage delete(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;

        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            message = new ResponseMessage(0, "删除成功", null);
            return message;
        }



        return message;

    }

}
