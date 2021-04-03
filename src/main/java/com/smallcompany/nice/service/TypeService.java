package com.smallcompany.nice.service;

import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Mng_atKey;

import java.util.List;

/**
 * @Author Song
 * @Date 2021/3/28 17:52
 * @Version 1.0
 */
public interface TypeService {
    public Mng_atKey insertMngAt(Integer mngId, Integer typeId);

    public List<Authoritytype> getTypes();

    public Integer addAtType(Authoritytype authoritytype);
}
