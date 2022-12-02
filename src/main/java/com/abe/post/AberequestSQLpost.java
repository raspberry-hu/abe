package com.abe.post;

import com.abe.mapper.AberequestMapper;
import com.abe.service.AberequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
@Slf4j
public class AberequestSQLpost {
    @Autowired
    private AberequestMapper aberequestMapper;
    @Autowired
    private AberequestService aberequestService;

    public static AberequestSQLpost sqLpost;
    @PostConstruct
    public void  init(){
        sqLpost=this;
        sqLpost.aberequestMapper=this.aberequestMapper;
        sqLpost.aberequestService=this.aberequestService;
    }
}
