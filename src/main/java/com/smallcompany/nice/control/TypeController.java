package com.smallcompany.nice.control;

import com.alibaba.fastjson.JSON;
import com.smallcompany.nice.dao.AuthoritytypeMapper;
import com.smallcompany.nice.dao.ManagerMapper;
import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.service.TypeService;
import com.smallcompany.nice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Song
 * @Date 2021/3/31 11:06
 * @Version 1.0
 */
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Resource(name = "authoritytypeMapper")
    private AuthoritytypeMapper authoritytypeMapper;

    @ResponseBody
    @RequestMapping(value = "getType",method = {RequestMethod.POST,RequestMethod.GET})
    public String getType(HttpServletRequest req) {
        HashMap<String, Object> map = new HashMap<>();
        List<Authoritytype> authoritytypes=typeService.getTypes();
        map.put("code",200);
        map.put("authoritytypes",authoritytypes);
        return JSON.toJSONString(map);
    }
}
