package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Mng_atKey;
import org.springframework.stereotype.Repository;

@Repository(value = "mng_atMapper")
public interface Mng_atMapper {
    int deleteByPrimaryKey(Mng_atKey key);

    int insert(Mng_atKey record);

    int insertSelective(Mng_atKey record);
}