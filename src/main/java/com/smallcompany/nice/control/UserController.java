package com.smallcompany.nice.control;

/**
 * @Author Song
 * @Date 2021/2/20 18:24
 * @Version 1.0
 */

import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用户管理子模块，负责用户的添加，修改，删除,注册，登录
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * @Description: 注册
     * @Param: User
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: Song
     * @Date: 2020/11/17
     */

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> register(@RequestBody Manager m) {
        HashMap<String, Object> map = new HashMap<>();
        Integer id = userService.getManagerbyPhone(m.getMngTel());
        if (m.getMngPwd() == null) {
            map.put("status", 202);
            map.put("note", "密码错误");
        } else if (id != null) {
            map.put("status", 201);
            map.put("note", "该手机号已注册");
        } else {
            Manager user= userService.insertManager(m);
            map.put("status", 200);
            map.put("user",user);
        }
        return map;
    }
}
