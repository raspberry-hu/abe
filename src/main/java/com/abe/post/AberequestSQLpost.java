package com.abe.post;

import com.abe.bean.abefile;
import com.abe.bean.aberequest;
import com.abe.mapper.AberequestMapper;
import com.abe.service.AberequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
    public String addRequest(Integer id, Integer fileId, Integer providerId, String policy){
        aberequest aberequest = new aberequest();
        aberequest.setFile_id(fileId);
        aberequest.setRequester_id(id);
        aberequest.setProvider_id(providerId);
        aberequest.setAttribute(policy);
        sqLpost.aberequestMapper.insert(aberequest);
        return "插入成功";
    }
}
