package com.smallcompany.nice.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Song
 * @Date 2021/3/24 14:16
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class InterfaceController {
    @RequestMapping(value = {"login", "/"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String toindex() {
        return "index";
    }

    /*
    解决iframe标签中src问题
     */
    //管理员列表
    @RequestMapping(value = {"admin-list"}, method = RequestMethod.GET)
    public String toAdmin() {
        return "admin-list";
    }

    //控制台
    @RequestMapping(value = {"console"}, method = RequestMethod.GET)
    public String toconsole() {
        return "console";
    }

    //权限列表
    @RequestMapping(value = {"auth-list"}, method = RequestMethod.GET)
    public String toAuth() {
        return "auth-list";
    }

    //人员列表
    @RequestMapping(value = {"article-list"}, method = RequestMethod.GET)
    public String toPeopleList() {
        return "article-list";
    }

    //人员类型
    @RequestMapping(value = {"article_category-list"}, method = RequestMethod.GET)
    public String toPeopleType() {
        return "article_category-list";
    }

    //添加管理员
    @RequestMapping(value = {"admin-operation"}, method =RequestMethod.GET)
    public String toAddAdmin() {
        return "admin-operation";
    }

    //添加权限
    @RequestMapping(value = {"auth_operation"}, method =RequestMethod.GET)
    public String toAddAdminType() {
        return "auth_operation";
    }

    //添加人员
    @RequestMapping(value = {"article_operation"}, method =RequestMethod.GET)
    public String toAddPeople() {
        return "article_operation";
    }

    //添加人员类型
    @RequestMapping(value = {"article_category_operation"}, method =RequestMethod.GET)
    public String toAddPeopleType() {
        return "article_category_operation";
    }

}
