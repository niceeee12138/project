package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "authoritytypeMapper")
public interface AuthoritytypeMapper {
    int deleteByPrimaryKey(Integer atId);

    int insert(Authoritytype record);

    int insertSelective(Authoritytype record);

    Authoritytype selectByPrimaryKey(Integer atId);

    int updateByPrimaryKeySelective(Authoritytype record);

    int updateByPrimaryKey(Authoritytype record);

    Authoritytype selectByName(String atName);

    List<Authoritytype> findAll();
}