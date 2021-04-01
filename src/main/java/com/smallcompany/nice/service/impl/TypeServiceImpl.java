package com.smallcompany.nice.service.impl;

import com.smallcompany.nice.dao.AuthoritytypeMapper;
import com.smallcompany.nice.dao.ManagerMapper;
import com.smallcompany.nice.dao.Mng_atMapper;
import com.smallcompany.nice.model.Authoritytype;
import com.smallcompany.nice.model.Manager;
import com.smallcompany.nice.model.Mng_atKey;
import com.smallcompany.nice.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Song
 * @Date 2021/3/28 17:52
 * @Version 1.0
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Resource(name = "mng_atMapper")
    private Mng_atMapper mng_atMapper;
    @Resource(name = "authoritytypeMapper")
    private AuthoritytypeMapper authoritytypeMapper;

    @Override
    @Transactional
    public Mng_atKey insertMngAt(Integer mngId,Integer typeId){
        Mng_atKey mng_atKey=new Mng_atKey();
        mng_atKey.setAtId(typeId);
        mng_atKey.setMngId(mngId);
        mng_atMapper.insertSelective(mng_atKey);
        return mng_atKey;
    }


    @Override
    public List<Authoritytype> getTypes() {
        List<Authoritytype> authoritytypes=authoritytypeMapper.findAll();
        return authoritytypes;
    }
}
