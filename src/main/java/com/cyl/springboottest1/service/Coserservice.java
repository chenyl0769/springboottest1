package com.cyl.springboottest1.service;

import com.cyl.springboottest1.entity.Coser;
import org.springframework.stereotype.Service;

@Service
public interface Coserservice {
    public Coser findcoserbyid(Integer id);
}
