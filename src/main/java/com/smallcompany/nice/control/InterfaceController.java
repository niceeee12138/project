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
    @RequestMapping(value = {"admin-list"}, method = RequestMethod.GET)
    public String toAdmin() {
        return "admin-list";
    }

    @RequestMapping(value = {"console"}, method = RequestMethod.GET)
    public String toconsole() {
        return "console";
    }

    @RequestMapping(value = {"auth-list"}, method = RequestMethod.GET)
    public String toAuth() {
        return "auth-list";
    }

    @RequestMapping(value = {"article-list"}, method = RequestMethod.GET)
    public String toPeopleList() {
        return "article-list";
    }

    @RequestMapping(value = {"article_category-list"}, method = RequestMethod.GET)
    public String toPeopleType() {
        return "article_category-list";
    }

    //@ResponseBody
    @RequestMapping(value = {"admin-operation"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String toAddAdmin() {
        return "admin-operation";
    }
}
