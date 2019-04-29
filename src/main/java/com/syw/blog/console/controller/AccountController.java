package com.syw.blog.console.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.syw.blog.config.service.RedisService;
import com.syw.blog.entity.Account;
import com.syw.blog.entity.Power;
import com.syw.blog.ptool.AnalysisUtil;
import com.syw.blog.ptool.MD5Util;
import com.syw.blog.ptool.ResponseMessage;
import com.syw.blog.service.AccountService;
import com.syw.blog.service.PowerService;
import org.apache.juneau.dto.html5.I;
import org.apache.juneau.dto.html5.Li;
import org.apache.juneau.json.annotation.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 后台管理员控制器
 */
@RestController
@RequestMapping("/console/account")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private MD5Util md5;

    /**
     * 管理员登录
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseMessage login(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;
        boolean b = false;
        Account account = null;
        String password = null;
        String username = null;
        try {

            account = (Account) session.getAttribute("account");
            if (account != null) {
                //session中account不爲空，无需进行登录操作，比对密码是否有修改，有修改需要重新登录
                logger.info("用户{}在session中的信息展示{}", account.getAccount(), account.toString());
                username = account.getAccount();
                password = account.getPassword();
            } else {
                //session中account為空，进行登录操作
                username = (String) request.get("account");
                password = md5.EncoderByMD5((String) request.get("password"));
            }
            Account checkAccount = accountService.findByAccount(username);

            //密码验证
            b = md5.checkPassword(password, checkAccount.getPassword());

            if (b) {
                session.setAttribute("account", checkAccount);
                message = new ResponseMessage(0, "登录成功", checkAccount);
            } else {
                message = new ResponseMessage(-1, "登录失败,请重新输入密码", null);
            }

            //设置session过期时间(单位:s)
            session.setMaxInactiveInterval(30 * 60);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return message;

    }

    /**
     * 管理员添加
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage insertAccount(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;

        Account sessionAccount = (Account) session.getAttribute("account");
        if (sessionAccount == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        String username = (String) request.get("username");
        String password = (String) request.get("password");

        Account account = new Account();
        account.setAccount(username);
        account.setPassword(password);
        account.setCreateAccount(sessionAccount.getId());
        account.setUpdateAccount(sessionAccount.getId());
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setIsDel(0);

        int i = accountService.insertAccount(account);

        if (i == 1) {
            message = new ResponseMessage(0, "添加成功", null);
        } else {
            message = new ResponseMessage(-1, "添加失败", null);
        }

        return message;

    }

    /**
     * 管理员管理列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Account.AccountSimpleView.class)
    public ResponseMessage queryAccountList(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;
        Map<String, Object> param = new HashMap<>();
        param.put("isDel", 0);
        List<Account> data = accountService.queryAccountByLimit(param);

        message = new ResponseMessage(0, "查询成功", data);

        return message;

    }

    /**
     * 管理员详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Account.AccountDetailView.class)
    public ResponseMessage queryAccountById(@RequestBody Map<String, Object> request) {

        ResponseMessage message = null;
        Account account = null;
        Integer id = (Integer) request.get("id");

        if (id != null) {
            account = accountService.queryAccountById(id);
            message = new ResponseMessage(0, "查询成功", account);
        } else {
            message = new ResponseMessage(-1, "查询失败", account);
        }

        return message;

    }

    /**
     * 管理员信息修改(有待修改)
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage updateAccount(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;
        Account sessionAccount = (Account) session.getAttribute("account");
        if (sessionAccount == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        List<Integer> powers = (List<Integer>) request.get("powers");
        String password = (String) request.get("password");
        Integer id = (Integer) request.get("id");

        try {

            Account account = accountService.queryAccountById(id);
            if (account != null) {
                account.setPassword(md5.EncoderByMD5(password));
                account.setPower(powers.toString());
                account.setUpdateTime(new Date());
                account.setUpdateAccount(sessionAccount.getId());
                int i = accountService.updateAccount(account);
                if (i == 1){
                    if (id == sessionAccount.getId()) {
//                        session.removeAttribute("account");
                        session.setAttribute("account", account);
                    }
                    message = new ResponseMessage(0, "修改成功", null);
                } else {
                    message = new ResponseMessage(-1, "修改失败", null);
                }
            } else {
                message = new ResponseMessage(0, "查无此用户，请确认要修改的管理员", null);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        session.setMaxInactiveInterval(30 * 60);

        return message;

    }

    /**
     * 管理员删除(逻辑删除)
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage deleteAccount(@RequestBody Map<String, Object> request, HttpSession session) {

        ResponseMessage message = null;

        Account sessionAccount = (Account) session.getAttribute("account");
        if (sessionAccount == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        Integer id = (Integer) request.get("id");

        if (id != null) {
            Account account = accountService.queryAccountById(id);
            if (account != null) {
                account.setIsDel(1);
                account.setUpdateAccount(sessionAccount.getId());
                account.setUpdateTime(new Date());
                int i = accountService.updateAccount(account);
                if (i == 1){
                    if (id == sessionAccount.getId()) {
                        session.setAttribute("account", account);
                    }
                    message = new ResponseMessage(0, "删除成功", null);
                } else {
                    message = new ResponseMessage(-1, "删除失败", null);
                }
            } else {
                message = new ResponseMessage(0, "查无此用户，请确认要删除的管理员", null);
            }
        } else {
            message = new ResponseMessage(0, "请选择一个要删除的用户", null);
        }

        session.setMaxInactiveInterval(30 * 60);

        return message;

    }

    /**
     * 加载资源
     * @param session
     * @return
     */
    @RequestMapping(value = "/lodingPower", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(Power.PowerLodingView.class)
    public ResponseMessage lodingPower(HttpSession session) {

        ResponseMessage message = null;
        //判断是否需要重新登录
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            message = new ResponseMessage(0, "请重新登录", null);
            return message;
        }

        //获取当前管理员权限列表
        List<Integer> ids = AnalysisUtil.AnalysisList(account.getPower());
        List<Integer> redisIds = new ArrayList<>();
        List<Object> objects = null;
        boolean redistag = false;

        //判断是否存在缓存
        if (redisService.exists("power")) {
            //获取缓存中权限列表
            objects = redisService.lRange("power", 0, 2);
            for (Object o : objects) {
                if (o instanceof Power) {
                    if (((Power) o).getFatherCode() != 0) {
                        redisIds.add(((Power) o).getId());
                    }
                }
            }
            for (Integer i : ids) {
                for (Integer id : redisIds) {
                    redistag = i == id;
                }
            }
        } else {
            redistag = false;
        }
        List<Power> list = new ArrayList<>();

        //判断是否从缓存中取值
        if (redistag) {
            for (Object o : objects) {
                if (o instanceof Power) {
                    logger.info("o转power:{}", o.toString());
                    list.add((Power) o);
                }
            }
            logger.info("从缓存中获取的资源列表:{}", list);
        } else {
            for (Integer id : ids) {
                Power power = powerService.queryPowerById(id);
                if (power.getFatherCode() != 0) {
                    Power fatherPower = powerService.queryPowerById(power.getFatherCode());
                    for (Power p : list) {
                        if (p.getId() != fatherPower.getId()) {
//                            redisService.lPush("powerIds", fatherPower.getId());
                            redisService.lPush("power", fatherPower);
                            list.add(fatherPower);
                            logger.info("没有的元素进行添加:{},boolean为{}", fatherPower, list.contains(fatherPower));
                            break;
                        }
                    }
                }
//                redisService.lPush("powerIds", power.getId());
                redisService.lPush("power", power);
                list.add(power);
            }
            logger.info("从数据库中获取的资源列表:{}", list);
        }

        //资源加载是否正确日志
        logger.info("资源加载的资源:{}", list);

        if (list.size() != 0) {
            message = new ResponseMessage(0, "加载资源成功", list);
        } else {
            message = new ResponseMessage(-1, "加载资源失败", null);
        }

        return message;

    }

    /**
     * 清除session操作
     * @param session
     * @return
     */
    @RequestMapping(value = "/removeSession", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseMessage removeSession(HttpSession session) {

        session.removeAttribute("account");

        return new ResponseMessage(0, "清除成功", null);

    }

    /**
     * 清redis缓存操作
     */
    @RequestMapping(value = "/removeRedis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void removeRedis() {
        redisService.remove("powerIds");
    }

}
