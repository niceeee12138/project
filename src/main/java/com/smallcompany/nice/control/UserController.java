package com.smallcompany.nice.control;

/**
 * @Author Song
 * @Date 2021/2/20 18:24
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.model.ResponseJson;
import com.smallcompany.nice.service.UserService;
import com.smallcompany.nice.until.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;

/**
 * 用户管理子模块，负责用户的添加，修改，删除,注册，登录
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * @Description: 登录
     * @Param: [User]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: Song
     * @Date: 2020/10/25
     */
    @ResponseBody
    @RequestMapping(value = "login",method = {RequestMethod.POST})
    public String longin(HttpServletRequest req) {
        HashMap<String, Object> map = new HashMap<>();
        Manager m = new Manager();
        String mngId=req.getParameter("mngId");
        String mngPwd=req.getParameter("mngPwd");
        Integer mngIdInt = null;
        if(mngId!=null){
            mngIdInt = Integer.valueOf(mngId);
        }
        m.setMngId(mngIdInt);
        m.setMngPwd(mngPwd);
//        System.out.println(m);
   Manager m1 = userService.isLogin(m);
        if (m1 == null) {
            //代表登录失败
            map.put("code",0);
        } else {
            //代表登录成功
            map.put("code",200);
            System.out.println("登录成功");
            //生成Token令牌
            String token = UUID.randomUUID() + "";
            //存到Redis数据库
            redisTemplate.opsForValue().set(token, m1, Duration.ofDays(1));
            map.put("token", token);
        }
        return JSON.toJSONString(map);
    }

    /**
     * @Description: 注册(添加管理员)
     * @Param: User
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: Song
     * @Date: 2020/11/17
     */

    @RequestMapping(value = "register", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> register(@RequestBody JSONArray jsonArray) {
        String token = (String) jsonArray.getJSONObject(0).get("token");
        System.out.println(token);
        Object manager1 = redisTemplate.opsForValue().get(token);
        Manager manager2= Tool.JsonToManager(jsonArray.getJSONObject(1));
        HashMap<String, Object> map = new HashMap<>();
        Integer id = userService.getManagerbyPhone(manager2.getMngTel());
        if (manager1!=null){
            if (manager2.getMngPwd() == null) {
                map.put("status", 202);
                map.put("note", "密码错误");
            } else if (id != null) {
                map.put("status", 201);
                map.put("note", "该手机号已注册");
            } else {
                Manager manager= userService.insertManager(manager2);
                map.put("status", 200);
                map.put("manager",manager);
            }
        }else{
            map.put("status", 204);
        }

        return map;
    }
    /**
            * @Description: 退出登录 
            * @Param: [object]
            * @return: java.util.HashMap<java.lang.String,java.lang.Object> 
            * @Author: Song 
            * @Date: 2021/2/25
            */
         
    @RequestMapping(value = "/signOut", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> signOut(@RequestBody JSONObject object) {
        String token = (String) object.get("token");
        System.out.println(token);
        Object manager1 = redisTemplate.opsForValue().get(token);
        HashMap<String, Object> map = new HashMap<>();
        if (manager1 != null) {
            redisTemplate.delete(token);
            map.put("status", 201);
            map.put("note", "删除成功");
        } else {
            map.put("status", 200);
            map.put("note","删除失败");
        }
        return map;
    }

    /**
            * @Description: 添加管理员类型 
            * @Param: [authoritytype] 
            * @return: java.util.HashMap<java.lang.String,java.lang.Object> 
            * @Author: Song 
            * @Date: 2021/2/23
            */
         
    @RequestMapping(value = "/addManagerType", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> addManagerType(@RequestBody Authoritytype authoritytype) {
        HashMap<String, Object> map = new HashMap<>();
        Integer id = userService.getAtName(authoritytype.getAtName());
        if (id != null) {
            map.put("status", 201);
            map.put("note", "该职位已存在");
        } else {
            Authoritytype authoritytype1= userService.insertAuthoritytype(authoritytype);
            map.put("status", 200);
            map.put("user",authoritytype1);
        }
        return map;
    }

    @RequestMapping(value = "/editManager", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> editManager(@RequestBody JSONArray jsonArray) {
        String token = (String) jsonArray.getJSONObject(0).get("token");
        System.out.println(token);
        Object manager1 = redisTemplate.opsForValue().get(token);
        HashMap<String, Object> map = new HashMap<>();
        
        return map;
    }


}
