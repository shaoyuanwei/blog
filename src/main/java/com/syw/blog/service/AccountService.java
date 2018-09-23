package com.syw.blog.service;

import com.syw.blog.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {

    Account findByAccount(String username);

    int insertAccount(Account account);

    List<Account> queryAccountByLimit(Map<String, Object> param);

    Account queryAccountById(Integer id);

    int updateAccount(Account account);

}
