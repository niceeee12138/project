package com.smallcompany.nice.control;

/**
 * @Author Song
 * @Date 2021/2/20 18:24
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smallcompany.nice.dao.ManagerMapper;
import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.model.Mng_atKey;
import com.smallcompany.nice.model.ResponseJson;
import com.smallcompany.nice.service.TypeService;
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
import java.util.List;
import java.util.UUID;

/**
 * 用户管理子模块，负责用户的添加，修改，删除,注册，登录
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Resource(name = "managerMapper")
    private ManagerMapper managerMapper;
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
    @ResponseBody
    @RequestMapping(value = "register", method =  RequestMethod.POST)
    public String register(HttpServletRequest req) {
        HashMap<String, Object> map = new HashMap<>();
        Manager m = new Manager();

        String mngNumber=req.getParameter("mngNumber");
        String mngPwd=req.getParameter("mngPwd");
        Integer mngTypeId=Integer.parseInt(req.getParameter("mngType"));
        String mngName=req.getParameter("mngName");
        String mngMobile=req.getParameter("mngMobile");
        m.setMngNumber(mngNumber);
        m.setMngStatus(1);
        m.setMngName(mngName);
        m.setMngTel(mngMobile);
        m.setMngPwd(mngPwd);

        Integer id = managerMapper.selectByTel(mngMobile).getMngId();
        if (id!=null){
            //代表登录失败
            map.put("code",0);
            System.out.println("注册失败");

        }
        else {
            Manager m1=userService.insertManager(m);
            Mng_atKey mng_atKey=typeService.insertMngAt(m1.getMngId(),mngTypeId);
            //代表登录成功
            map.put("code",200);
            System.out.println("注册成功");
        }
        return JSON.toJSONString(map);
    }


    //TODOSong:getManages接口的编写

    @ResponseBody
    @RequestMapping(value = "getManagers", method =  RequestMethod.POST)
    public String getManagers(HttpServletRequest req) {
        HashMap<Object, Object> map = new HashMap<>();
        List<Manager> managers=userService.findAllManagers();
        map.put("code",200);
        map.put("msg","");
        map.put("data",managers);
        map.put("count",managers.size());
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }
}
