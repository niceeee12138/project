package com.smallcompany.nice.service;

import com.smallcompany.nice.dao.ManagerMapper;
import com.smallcompany.nice.model.Manager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author Song
 * @Date 2021/2/20 18:26
 * @Version 1.0
 */
@Service
public class UserService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "managerMapper")
    private ManagerMapper managerMapper;

    public Integer getManagerbyPhone(String phone){
        Manager m = managerMapper.selectByTel(phone);
        if (m==null)
            return null;
        return m.getMngId();
    }

    @Transactional
    public Manager insertManager(Manager u){
        u.setMngStatus(0);
        managerMapper.insert(u);
        System.out.println(u.getMngId());
        return u;
    }
}
