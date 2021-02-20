package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Manager;
import org.springframework.stereotype.Repository;

@Repository(value = "managerMapper")
public interface ManagerMapper {
    int deleteByPrimaryKey(Integer mngId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer mngId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    Manager selectByTel(String mngtel);
}