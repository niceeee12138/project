package com.smallcompany.nice.dao;

import com.smallcompany.nice.model.Pp_ptKey;
import org.springframework.stereotype.Repository;

@Repository(value = "pp_ptMapper")
public interface Pp_ptMapper {
    int deleteByPrimaryKey(Pp_ptKey key);

    int insert(Pp_ptKey record);

    int insertSelective(Pp_ptKey record);
}