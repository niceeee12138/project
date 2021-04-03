package com.smallcompany.nice.service;

import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.model.Peolpletype;

import java.util.List;

/**
 * @Author Song
 * @Date 2021/3/14 21:11
 * @Version 1.0
 */
public interface UserService {
    public Integer getAtName(String atName);

    public Integer getManagerbyPhone(String phone);

    public Manager insertManager(Manager u);

    public Manager isLogin(Manager m);

    public Authoritytype insertAuthoritytype(Authoritytype a);

    public void insertPeolpleType(Peolpletype pt);

    public void updateByptId(Peolpletype pt);

    public void delPTByptId(Integer id);

    public List<Peolpletype> getAllPeopleType();


}
