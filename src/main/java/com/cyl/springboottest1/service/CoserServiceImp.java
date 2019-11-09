package com.cyl.springboottest1.service;

import com.cyl.springboottest1.entity.Coser;
import com.cyl.springboottest1.mappers.CoserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoserServiceImp implements Coserservice {
    @Autowired
    private CoserMapper coserMapper;
    @Override
    public Coser findcoserbyid(Integer id){

        return coserMapper.SelectCoserByUid(id);
    }
}
