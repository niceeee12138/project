package com.smallcompany.nice.until;

import com.alibaba.fastjson.JSONObject;
import com.smallcompany.nice.model.Manager;

/**
 * @Author Song
 * @Date 2021/2/24 18:11
 * @Version 1.0
 * 工具类
 */
public class Tool {
    public static Manager JsonToManager(JSONObject o){
        Manager manager=new Manager();
        try{
            manager.setMngTel((String)o.get("mngTel"));
            manager.setMngPwd((String)o.get("mngPwd"));
            manager.setMngStatus((Integer)o.get("mngStatus"));
            manager.setMngNumber((String)o.get("mngName"));
            manager.setMngNumber((String)o.get("mngNumber"));
        }catch (Exception e){
            throw new RuntimeException();
        }
        return manager;
    }
}
