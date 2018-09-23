package com.syw.blog.service.Impl;

import com.syw.blog.dao.AccountMapper;
import com.syw.blog.entity.Account;
import com.syw.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Override
    public Account findByAccount(String username) {
        return mapper.selectByUsername(username);
    }

    @Override
    public int insertAccount(Account account) {
        return mapper.insert(account);
    }

    @Override
    public List<Account> queryAccountByLimit(Map<String, Object> param) {
        return mapper.queryAccountByLimit(param);
    }

    @Override
    public Account queryAccountById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAccount(Account account) {
        return mapper.updateByPrimaryKeySelective(account);
    }
}
