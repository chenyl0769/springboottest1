package com.cyl.springboottest1.service;

import com.cyl.springboottest1.Coser;
import com.cyl.springboottest1.mappers.CoserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoserService {
    @Autowired
    private CoserMapper coserMapper;

    public Coser findcoserbyid(Integer id){

        return coserMapper.SelectCoserByUid(id);
    }
}
