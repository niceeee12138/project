package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Authoritytype;
import org.springframework.stereotype.Repository;

@Repository(value = "authoritytypeMapper")
public interface AuthoritytypeMapper {
    int deleteByPrimaryKey(Integer atId);

    int insert(Authoritytype record);

    int insertSelective(Authoritytype record);

    Authoritytype selectByPrimaryKey(Integer atId);

    int updateByPrimaryKeySelective(Authoritytype record);

    int updateByPrimaryKey(Authoritytype record);
}