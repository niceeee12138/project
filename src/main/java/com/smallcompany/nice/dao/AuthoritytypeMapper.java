package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Authoritytype;

import java.util.List;

public interface AuthoritytypeMapper {
    int deleteByPrimaryKey(Integer atId);

    int insert(Authoritytype record);

    int insertSelective(Authoritytype record);

    Authoritytype selectByPrimaryKey(Integer atId);

    int updateByPrimaryKeySelective(Authoritytype record);

    int updateByPrimaryKey(Authoritytype record);

    List<Authoritytype> findAll();

    Authoritytype selectByName(String atName);
}