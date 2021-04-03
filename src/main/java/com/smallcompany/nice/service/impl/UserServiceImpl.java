package com.smallcompany.nice.service.impl;

import com.smallcompany.nice.dao.AuthoritytypeMapper;
import com.smallcompany.nice.dao.ManagerMapper;
import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author Song
 * @Date 2021/3/14 21:09
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "managerMapper")
    private ManagerMapper managerMapper;
    @Resource(name = "authoritytypeMapper")
    private AuthoritytypeMapper authoritytypeMapper;
    @Override
    public Integer getManagerbyPhone(String phone){
        Manager m = managerMapper.selectByTel(phone);
        if (m==null)
            return null;
        return m.getMngId();
    }
    @Override
    @Transactional
    public Manager insertManager(Manager u){
        u.setMngStatus(0);
        managerMapper.insert(u);
        System.out.println(u.getMngId());
        return u;
    }

    /**
     * @Description: 是否可登录
     * @Param: [m]
     * @return: com.smallcompany.nice.model.Manager
     * @Author: Song
     * @Date: 2021/2/24
     */
    @Override
    public Manager isLogin(Manager m) {
        Manager m1 = managerMapper.selectByPrimaryKey(m.getMngId());
        if(m1==null){
            return null;
        }
        if(m1.getMngPwd().equals(m.getMngPwd())){
            return m1;
        }
        return null;
    }
    @Override
    public Integer getAtName(String atName){
        Authoritytype a = authoritytypeMapper.selectByName(atName);
        if (a==null)
            return null;
        return a.getAtId();
    }
    @Override
    @Transactional
    public Authoritytype insertAuthoritytype(Authoritytype a){
        authoritytypeMapper.insert(a);
        System.out.println(a.getAtId());
        return a;
    }
}
