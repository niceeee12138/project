package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Peolpletype;
import org.springframework.stereotype.Repository;

@Repository(value = "peolpletypeMapper")
public interface PeolpletypeMapper {
    int deleteByPrimaryKey(Integer ptId);

    int insert(Peolpletype record);

    int insertSelective(Peolpletype record);

    Peolpletype selectByPrimaryKey(Integer ptId);

    int updateByPrimaryKeySelective(Peolpletype record);

    int updateByPrimaryKey(Peolpletype record);
}