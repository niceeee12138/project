package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.People;
import org.springframework.stereotype.Repository;

@Repository(value = "peopleMapper")
public interface PeopleMapper {
    int deleteByPrimaryKey(Integer ppId);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer ppId);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}